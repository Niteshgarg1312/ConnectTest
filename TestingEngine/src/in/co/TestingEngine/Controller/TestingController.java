 package in.co.TestingEngine.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.co.TestingEngine.Modal.User_Details;
import in.co.TestingEngine.Service.TestingService;

@RestController
public class TestingController {
	
	@Autowired
	TestingService testingService;
 
	@RequestMapping(value="normalSignUp")
	public int signUp(User_Details u)
	{
		String fileName= null;
		fileName= doUploadImage(u);
		u.setUserPhotoLink(fileName);
		int i=testingService.getUserSignUp(u);
		return i;
	}
	private String doUploadImage(User_Details fileUploadForm) {
    	String uploadRootPath =  "D:\\springJava\\TestingEngine\\WebContent\\images\\userImages";
    	File uploadRootDir = new File(uploadRootPath);
    	if (!uploadRootDir.exists()) {
    		uploadRootDir.mkdirs();
    	}
    	MultipartFile fileData = fileUploadForm.getPhoto();
    	String uploadedFiles = "";
    	String name = fileData.getOriginalFilename();

    	if (name != null && name.length() > 0) {
    		try {
    			File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
    			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
    			stream.write(fileData.getBytes());
    			stream.close();
    			System.out.println(
    					"Write file: " + serverFile.getCanonicalPath() + " :: " + serverFile.getAbsolutePath());
    			uploadedFiles = serverFile.getCanonicalPath();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else {
    		uploadedFiles = null;
    	}
    	
    	return uploadedFiles;
    }

	@RequestMapping(value="normalSignIn")
	public int signIn(User_Details u)
	{
		int i=testingService.getUserSignIn(u);
		return i;
	}
    	
	
}
