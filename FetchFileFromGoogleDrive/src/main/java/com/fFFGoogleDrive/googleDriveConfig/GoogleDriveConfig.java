package com.fFFGoogleDrive.googleDriveConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;



import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fFFGoogleDrive.localDrive.LocalDrive;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class GoogleDriveConfig {

	private static final String APPLICATION_NAME = "FetchFileFromGoogleDrive";
	
	private static final String CLIENT_ID = "296565525766-5gsntuve58pq4c0a1uku81chsul2ou1e.apps.googleusercontent.com";
	
	private static final String CLIENT_SECRET = "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";
	
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		
		log.info("Initializing getCredentials service...entry ..");
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				CLIENT_ID, CLIENT_SECRET, SCOPES)
			
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
			
				.setAccessType("offline").build();
		
		log.info("Initializing getCredentials service...GoogleAuthorizationCodeFlow build successfully ..");
		
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost("localhost").setPort(2323).build();
		
		log.info("Initializing getCredentials service...LocalServerReceiver build successfully ..");
		
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public static Drive getInstance() throws Exception {
		
		log.info("Initializing Google Drive service...");
		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		
		log.info("Initializing Google Drive service...HTTP_TRANSPORT ..");
		
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		
		log.info("Google Drive service initialized successfully.");
		
		return service;
	}

	public MultipartFile getFileByName(String fileName) throws Exception {
		
		log.info("Fetching file from Google Drive with name: " + fileName);
	
		Drive service = getInstance();
	
		FileList result = service.files().list().setQ("name='" + fileName + "'").setSpaces("drive").execute();
	
		List<File> files = result.getFiles();
	
		log.info("File fetched successfully.");
	
		if (files != null && !files.isEmpty()) {
		
			File googleDriveFile = files.get(0);
		
			return convertToMultipartFile(service, googleDriveFile);
		} else {
		
			log.warn("File '" + fileName + "' not found.");
		
			return null;
		}
	}
	

	
	
	
	 public MultipartFile convertToMultipartFile(String filePath) throws IOException {
	        Path path = Paths.get(filePath);
	        String fileName = path.getFileName().toString();
	        String contentType = Files.probeContentType(path);
	        byte[] content = Files.readAllBytes(path);
	        return new LocalDrive(content, fileName, fileName, contentType);
	    }
	
	
	
	
	

	private static MultipartFile convertToMultipartFile(Drive service, File file) throws IOException {
		InputStream inputStream = service.files().get(file.getId()).executeMediaAsInputStream();
		return new MultipartFile() {
			@Override
			public String getName() {
				return file.getName();
			}

			@Override
			public String getOriginalFilename() {
				return file.getName();
			}

			@Override
			public String getContentType() {
				return file.getMimeType();
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public long getSize() {
				try {
					return inputStream.available();
				} catch (IOException e) {
					log.warn("Failed to get input stream size: " + e.getMessage());
					return 0;
				}
			}

			@Override
			public byte[] getBytes() throws IOException {
				return inputStream.readAllBytes();
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return inputStream;
			}

			@Override
			public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
				// Transfer file content to the destination file if needed
			}
		};
	}

}
