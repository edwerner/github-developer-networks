package github.developer.networks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.CommitService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
	
	private ArrayList<Edge> edgeList = new ArrayList<Edge>();
	private final static String USER_AGENT = "Mozilla/5.0";
	private static final String OAUTH_KEY = "29f62521efacbbd90fb65036b3ce62a7e172cdd3";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		parseEdges();
//		final int size = 25;
//		final RepositoryId repo = new RepositoryId("eclipse", "che");
//		final CommitService service = new CommitService();
//		for (Collection<RepositoryCommit> commits : service.pageCommits(repo, size)) {
//			for (RepositoryCommit commit : commits) {
//				Thread.sleep(5000);
//				String sha = commit.getSha().substring(0, 7);
//				String author = commit.getCommit().getAuthor().getName();
//				Date date = commit.getCommit().getAuthor().getDate();
//				String email = commit.getCommit().getAuthor().getEmail();
//				try {
//					// GET /repos/:owner/:repo/commits/:commit_sha
//					sendGet("https://api.github.com/repos/eclipse/che/commits/" + sha);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}
	
	private static void sendGet(String url) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Authorization", "Bearer " + OAUTH_KEY);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		
		FileWriter writer = new FileWriter("commits.txt", true);
		BufferedWriter bw = new BufferedWriter(writer);

		while ((inputLine = in.readLine()) != null) {
			bw.write(inputLine);
			bw.newLine();
		}
		in.close();
		bw.close();
	}
	
	public static void parseEdges() {
		Node userNode = null;
		JsonObject parserObject;
		String fileName = "commits.txt";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = (JsonObject) new JsonParser().parse(line);
				JsonObject nodeObject = parserObject.getAsJsonObject("commit");
				JsonObject nodeAuthor = nodeObject.getAsJsonObject("author");
				String nodeEmail = nodeAuthor.get("email").getAsString();
				System.out.println(nodeEmail);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
