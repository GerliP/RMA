package rma.project.rma.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rma.project.rma.Entity.Claim;
import rma.project.rma.Entity.Mail;
import rma.project.rma.service.ClaimService;
import rma.project.rma.service.DeviceService;
import rma.project.rma.service.EmailService;

/**
 * Created by gerli on 06/04/2018.
 */
@Controller
public class WarrantyFormController {

    @Autowired
    public DeviceService deviceService;
    @Autowired
    public ClaimService claimService;
    @Autowired
    public EmailService emailService;

    @GetMapping("/warranty")
    public String warrantyForm() {
        return "warranty";
    }

    @PostMapping("/warranty")
    public String create(Claim claim) {
        Claim newClaim = claimService.create(claim);
        Mail mail = new Mail();
        mail.setFrom("no-reply@RMA.com");
        mail.setTo("claimrma@gmail.com");
        mail.setSubject("New RMA claim: " );
        mail.setContent(newClaim.toString());

        emailService.sendEmail(mail);
        return "claimConfirmation";
    }

    @GetMapping("/claimConfirmation")
    public String claimConf() {
        return "claimConfirmation";
    }
    }
