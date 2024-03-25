package com.fFFGoogleDrive.localDrive;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;



import org.springframework.web.multipart.MultipartFile;
//@Service
public class LocalDrive implements MultipartFile {
	
	
	  private final byte[] content;
	    private final String name;
	    private final String originalFilename;
	    private final String contentType;

	    public LocalDrive(byte[] content, String name, String originalFilename, String contentType) {
	        super();
	        this.content = content;
	        this.name = name;
	        this.originalFilename = originalFilename;
	        this.contentType = contentType;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getOriginalFilename() {
	        return originalFilename;
	    }

	    public String getContentType() {
	        return contentType;
	    }

	    public boolean isEmpty() {
	        return content == null || content.length == 0;
	    }

	    public long getSize() {
	        return content.length; // Return the size of the content in bytes
	    }

	    public byte[] getBytes() throws IOException {
	        return content;
	    }

	    public InputStream getInputStream() throws IOException {
	        return new java.io.ByteArrayInputStream(content);
	    }

	    public void transferTo(File dest) throws IOException, IllegalStateException {
	        // Implement transfer logic here
	    }


}
