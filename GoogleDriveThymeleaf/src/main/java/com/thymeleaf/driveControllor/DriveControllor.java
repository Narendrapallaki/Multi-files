package com.thymeleaf.driveControllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.thymeleaf.driveService.DriveService;
import com.thymeleaf.entity.EmailForm;
import com.thymeleaf.entity.FileDetails;
import com.thymeleaf.entity.Form;
import com.thymeleaf.entity.SaveMailData;
import com.thymeleaf.mailDataRepo.FileDataRepo;
import com.thymeleaf.mailDataRepo.MailDataRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequestMapping("/")
public class DriveControllor {

	@Autowired
	private DriveService driveService;
	@Autowired
	private MailDataRepo dataRepo;
	@Autowired
	private FileDataRepo fileDataRepo;
	
	
	
	 @GetMapping("/form")
	    public String showForm(Form user, Model model) {
	        model.addAttribute("user", user);
	        return "form";
	    }

	 @GetMapping("/forTesting")
	    public String formTesting(@ModelAttribute("user") Form form, Model model) {
	        String myMail = "narendrapallaki@gmail.com";

	        if (form.getEmail().equals(myMail)) {
	            return "redirect:/login";
	        } else {
	            // Add a message to indicate invalid email
	        	String error="Email does not match";
	            model.addAttribute("errorMessage", error);
	            return "form"; // Return to the form page
	        }
	    }

	
	@GetMapping("/deleteFile/{fileName}")
	public String deleteFileName(@PathVariable("fileName") String fileName, Model model) {
		
		            FileDetails byFileName = fileDataRepo.findByFileName(fileName);
		            log.info("delete data form db in to controllor :{}",byFileName);
		          
						
		            	fileDataRepo.delete(byFileName);
				
		return "redirect:/deleteFolder";
	}
	
	
	@GetMapping("/deleteFolder")
	public String listOfFiles(Model model) {

		 List<FileDetails> fileData = driveService.getFileData();
		model.addAttribute("files", fileData);
                    List<FileDetails> all = fileDataRepo.findAll();
                    model.addAttribute("folderList", all);
		return "delete";
	}

	
	
	/*
	 * @GetMapping("/deleteFolder") public String deleteFolder() { return "delete";
	 * }
	 */
	@GetMapping("/deleteAll")
	public String  deleteFolder()
	{
		
		fileDataRepo.deleteAll();
		
		return "redirect:/deleteFolder";
	}

	@GetMapping("/emailForm")
	public String emailForm() {

		
		
		
	
		return "mailForm";
	}
	
	@GetMapping("/emailFormname")
	public String emailForm(Model model) {
	    model.addAttribute("emailForm", new EmailForm());
	    return "mailFormDummy";
	}




	@PostMapping("/formPost")
	public String formPost(@ModelAttribute EmailForm emailForm,
			@RequestParam(name = "to", required = false) MultipartFile to,
			@RequestParam(name = "zip", required = false) MultipartFile zip,Model model) {

		log.info("email form :{}", emailForm);
		log.info("excelFile :{}", to.getOriginalFilename());
		log.info("zip file :{}", zip.getOriginalFilename());

		SaveMailData byMail = driveService.getByMail(emailForm.getFrom());

		  try {
	        if (byMail==null) {
	            throw new RuntimeException("Email addresses do not match!");
	        } 
	    } catch (Exception e) {
	        // Set the error message attribute
	        model.addAttribute("errorMessage", e.getMessage());
	        return "mailForm"; // Return the name of your Thymeleaf template
	    }
		//return null;

	    return "redirect:/completed";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		SaveMailData byId = dataRepo.findById(id)

				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		log.info("delete user form db :{}", byId);
		dataRepo.delete(byId);
		return "redirect:/listUsers";
	}

	@PostMapping("/saveMailData")
	public String postSaveMailData(@ModelAttribute SaveMailData saveMailData) {

		log.info("saveMailData from controllor :{}", saveMailData);
		driveService.mailDataSave(saveMailData);

		return "redirect:/emailForm";
	}

	@GetMapping("/listUsers")
	public String listOfUsers(Model model) {

		List<SaveMailData> all = driveService.getAll();
		model.addAttribute("lists", all);

		return "UserList";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}

	@GetMapping("/completed")
	public String thankYou() {
		return "success";
	}

	@GetMapping("/signIn")
	public String signInPage() {
		return "signin";
	}

	@GetMapping("/redirectLogin")
	public String redirectLogin() {
		return "redirect:/login";
	}
}
