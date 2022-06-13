package hr.tvz.stambolija.hardwareapp.utility;

import hr.tvz.stambolija.hardwareapp.hardware.Hardware;
import hr.tvz.stambolija.hardwareapp.hardware.HardwareRepository;
import hr.tvz.stambolija.hardwareapp.hardware.HardwareService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.stream.Collectors;

public class AvailableHardwareJob extends QuartzJobBean {

    @Autowired
    private HardwareService hardwareService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Hardware> hardware = hardwareService.findAllInStock();

        System.out.println("Ovo su trenutno dostupni hardveri: ");
        System.out.println("-----------------------------------");

        for(int i = 0; i < hardware.size(); i++){
            System.out.println(hardware.get(i).getName() + " - " + hardware.get(i).getAmountInStorage());
        }

        System.out.println("-----------------------------------");
    }
}
