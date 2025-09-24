package  com.dreamjob.jobseeker.service;

import com.dreamjob.jobseeker.dto.ApplicationDto;
import com.dreamjob.jobseeker.model.JobSeeker;
import com.dreamjob.jobseeker.model.Skill;
import com.dreamjob.jobseeker.repository.JobSeekerRepository;
import com.dreamjob.jobseeker.repository.SkillRepository;
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

   public JobSeekerService(JobSeekerRepository repo, SkillRepository skillRepository, WebClient.Builder webClientBuilder) {
        this.jobSeekerRepository = repo;
        this.skillRepository = skillRepository;
        this.webClientBuilder = webClientBuilder;
   }

    public JobSeeker register(JobSeeker e) {
        JobSeeker saved = jobSeekerRepository.save(e);
        e.setStatus("ACTIVE");
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
            return jobSeekerRepository.save(existing);
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


}
