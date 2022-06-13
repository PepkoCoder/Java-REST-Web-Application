package hr.tvz.stambolija.hardwareapp.utility;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail availableJobDetail() {
        return JobBuilder.newJob(AvailableHardwareJob.class).withIdentity("availableJob").storeDurably().build();
    }

    @Bean
    public Trigger availableJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(3).repeatForever();
        return TriggerBuilder.newTrigger().forJob(availableJobDetail())
                .withIdentity("availableTrigger").withSchedule(scheduleBuilder).build();
    }

    @Bean
    public Trigger availableJobWorkDayTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 12 ? * MON-FRI");
        return TriggerBuilder.newTrigger().forJob(availableJobDetail())
                .withIdentity("availableTrigger").withSchedule(cronScheduleBuilder).build();
    }

}
