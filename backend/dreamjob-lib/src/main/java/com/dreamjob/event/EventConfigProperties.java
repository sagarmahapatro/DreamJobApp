package com.dreamjob.event;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


public class EventConfigProperties {

    private ServiceTopics jobapplication;
    private ServiceTopics jobseeker;
    private ServiceTopics jobopening;
    private ServiceTopics employer;

    public EventConfigProperties () {
        System.out.println("EventConfigProperties created >>>>>>>>>>>>>>>>>>>>>>>>>> ");
    }

    public ServiceTopics getJobapplication() { return jobapplication; }
    public void setJobapplication(ServiceTopics jobapplication) { this.jobapplication = jobapplication; }

    public ServiceTopics getJobseeker() { return jobseeker; }
    public void setJobseeker(ServiceTopics jobseeker) { this.jobseeker = jobseeker; }

    public ServiceTopics getJobopening() { return jobopening; }
    public void setJobopening(ServiceTopics jobopening) { this.jobopening = jobopening; }

    public ServiceTopics getEmployer() { return employer; }
    public void setEmployer(ServiceTopics employer) { this.employer = employer; }

    public static class ServiceTopics {
        private Map<String, String> producerTopics;
        private Map<String, String> consumerTopics;

        public ServiceTopics() {
           System.out.println(" %%%%%%%%%%%%%%%%%%%%%%%%%%b ServiceTopics created %%");
        }

        public Map<String, String> getProducerTopics() { return producerTopics; }
        public void setProducerTopics(Map<String, String> producerTopics) { this.producerTopics = producerTopics; }

        public Map<String, String> getConsumerTopics() { return consumerTopics; }
        public void setConsumerTopics(Map<String, String> consumerTopics) { this.consumerTopics = consumerTopics; }
    }
}
