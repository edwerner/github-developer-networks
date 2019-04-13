package github.developer.networks;

import java.util.ArrayList;
import java.util.UUID;

public class Node {
	
	@SuppressWarnings("unused")
	private String id;
	private String email;
	private ArrayList<String> fileList = new ArrayList<String>();
	
	public Node() {
		UUID uuid = UUID.randomUUID();
        String nodeId = uuid.toString();
        this.id = nodeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addFile(String filename) {
		fileList.add(filename);
	}
	
	public ArrayList<String> getFileList() {
		return this.fileList;
	}
}
