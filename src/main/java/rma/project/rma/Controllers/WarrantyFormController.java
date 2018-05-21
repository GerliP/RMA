package rma.project.rma.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import rma.project.rma.Repos.DeviceRepository;

/**
 * Created by gerli on 06/04/2018.
 */
@Controller
public class WarrantyFormController {

    @Autowired
    public DeviceRepository deviceRepository;



}
