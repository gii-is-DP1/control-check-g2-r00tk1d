package org.springframework.samples.petclinic.feeding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
public class FeedingController {
    private final FeedingService feedingService;
    private final PetService petService;

    @Autowired
    public FeedingController(FeedingService feedingService, PetService petservice){
        this.feedingService = feedingService;
        this.petService = petservice;
    }

    @ModelAttribute("feedingTypes")
    public Collection<FeedingType> loadFeedingTypes() {
        Collection<FeedingType> pTypes = this.feedingService.getAllFeedingTypes();
        return pTypes;
    }

    @ModelAttribute("pets")
    public Collection<Pet> loadPets() {
        Collection<Pet> pets = this.petService.getAllPets();
        return pets;
    }


    @GetMapping(value = "/feeding/create")
    public String initNewFeedingForm(ModelMap model) {
        Feeding feeding = new Feeding();
        model.put("feeding", feeding);
        return "feedings/createOrUpdateFeedingForm";
    }


    @PostMapping(value = "/feeding/create")
    public String processNewProductForm(@Valid Feeding feeding, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "feedings/createOrUpdateFeedingForm";
        }
        else {
            try{
                this.feedingService.save(feeding);
            }
            catch(UnfeasibleFeedingException e){

                return "feedings/createOrUpdateFeedingForm";
            }
            return "redirect:/welcome";
        }
    }
    
}
