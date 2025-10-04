package  com.dreamjob.jobseeker.service;

import com.dreamjob.jobseeker.dto.ApplicationDto;
import com.dreamjob.jobseeker.event.JobSeekerCreatedEvent;
import com.dreamjob.jobseeker.event.JobSeekerDeleteEvent;
import com.dreamjob.jobseeker.event.JobSeekerEvent;
import com.dreamjob.jobseeker.event.JobSeekerUpdatedEvent;
//import com.dreamjob.jobseeker.event.kafka.producer.JobSeekerEventProducer;
//import com.dreamjob.jobseeker.event.kafka.producer.JobSeekerEventProducerFactory;
import com.dreamjob.jobseeker.event.kafka.producer.JobSeekerEventProducerFactory;
import com.dreamjob.jobseeker.model.JobSeeker;
import com.dreamjob.jobseeker.model.Skill;
import com.dreamjob.jobseeker.repository.JobSeekerRepository;
import com.dreamjob.jobseeker.repository.SkillRepository;
import com.dreamjob.jobseeker.transformer.JobSeekerEventTransformer;
import com.dreamjob.jobseeker.transformer.JobSeekerEventTransformerFactory;
import com.dreamjob.transformer.Transformer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JobSeekerService {
    private final JobSeekerRepository jobSeekerRepository;
    private final SkillRepository skillRepository;
    private final WebClient.Builder webClientBuilder;
    private final JobSeekerEventTransformerFactory jobSeekerEventTransformerFactory;
    private final JobSeekerEventProducerFactory jobSeekerEventProducerFactory;


   public JobSeekerService(JobSeekerRepository repo,
                           SkillRepository skillRepository,
                           WebClient.Builder webClientBuilder,
                           JobSeekerEventTransformerFactory jobSeekerEventTransformerFactory,
                           JobSeekerEventProducerFactory jobSeekerEventProducerFactory) {
       this.jobSeekerRepository = repo;
       this.skillRepository = skillRepository;
       this.webClientBuilder = webClientBuilder;
       this.jobSeekerEventTransformerFactory = jobSeekerEventTransformerFactory;
       this.jobSeekerEventProducerFactory = jobSeekerEventProducerFactory;
   }




    public JobSeeker register(JobSeeker e) {
        JobSeeker saved = jobSeekerRepository.save(e);
        e.setStatus("ACTIVE");
        emitJobSeekerCreatedEvent(saved);
        return saved;
    }

    @Transactional
    public JobSeeker updateJobSeeker(JobSeeker jobSeeker) {
        Optional<JobSeeker> existingOpt = jobSeekerRepository.findById(jobSeeker.getId());
        if(existingOpt.isPresent()) {
            JobSeeker existing = existingOpt.get();
            if (jobSeeker.getSkills() != null) {
                Set<Skill> mergedSkills = new HashSet<>();
                for (Skill skill : jobSeeker.getSkills()) {
                    Skill existingSkill = skillRepository.findByName(skill.getName())
                            .orElseGet(() -> skillRepository.save(new Skill(skill.getName())));
                    mergedSkills.add(existingSkill);
                }
                jobSeeker.setSkills(mergedSkills);
            }
            existing.update(jobSeeker);
            JobSeeker jobSeekerSaved =  jobSeekerRepository.save(existing);
            emitJobSeekerUpdatedEvent(jobSeekerSaved);
            return jobSeekerSaved;
        } else {
            throw new RuntimeException("JobSeeker not found with id: " + jobSeeker.getId());
        }
    }

    public JobSeeker getJobSeeker(Long id) {
        return jobSeekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with id: " + id));
    }

    public List<JobSeeker> getAllJobSeeker() {
       return jobSeekerRepository.findAll();
    }

    public List<ApplicationDto> getJobSeekerApplications(Long jobSeekerId) {
        JobSeeker existing = getJobSeeker(jobSeekerId);
        return webClientBuilder.build()
                .get()
                .uri("http://application-service/applications/jobseeker/{id}", existing.getId())
                .retrieve()
                .bodyToFlux(ApplicationDto.class)  // replace Object with a DTO if you define one
                .collectList()
                .block();
   }

    public JobSeeker deleteJobSeeker(Long jobSeekerId) {
        JobSeeker existing = getJobSeeker(jobSeekerId);
        if(existing != null) {
            jobSeekerRepository.delete(existing);
            emitJobSeekerDeletedEvent(existing);
            return existing;
        } else {
            throw new RuntimeException("JobSeeker not found with id: " + jobSeekerId);
        }
    }

    private void emitJobSeekerDeletedEvent(JobSeeker existing) {
        emitJobSeekerEvent(existing,JobSeekerDeleteEvent.class);
    }

    private void emitJobSeekerCreatedEvent(JobSeeker existing) {
        emitJobSeekerEvent(existing,JobSeekerCreatedEvent.class);
    }

    private void emitJobSeekerUpdatedEvent(JobSeeker existing) {
        emitJobSeekerEvent(existing,JobSeekerUpdatedEvent.class);
    }

    private void emitJobSeekerEvent(JobSeeker existing, Class<? extends JobSeekerEvent> clazz) {
       JobSeekerEventTransformer  jobSeekerEventTransformer =  jobSeekerEventTransformerFactory.create(clazz);
       Optional<JobSeekerEvent> jobSeekerEventOpt  = jobSeekerEventTransformer.transform(existing);

        if(jobSeekerEventOpt.isPresent()) {
            JobSeekerEvent jobSeekerEvent = jobSeekerEventOpt.get();
            EventEmitter eventEmitter = jobSeekerEventProducerFactory.getProducer(clazz);
            eventEmitter.produce(jobSeekerEvent);
        } else {
            throw new RuntimeException("Failed to transform JobSeeker to JobSeekerDeleteEvent");
        }
    }
}
