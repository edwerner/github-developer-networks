package github.developer.networks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

public class App {
	
	private ArrayList<Edge> edgeList = new ArrayList<Edge>();
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws IOException {
		final int size = 5;
		final RepositoryId repo = new RepositoryId("eclipse", "che");
		final CommitService service = new CommitService();
		for (Collection<RepositoryCommit> commits : service.pageCommits(repo, size)) {
			for (RepositoryCommit commit : commits) {
				String sha = commit.getSha().substring(0, 7);
				String author = commit.getCommit().getAuthor().getName();
				Date date = commit.getCommit().getAuthor().getDate();
				String email = commit.getCommit().getAuthor().getEmail();
				
				//GET /repos/:owner/:repo/commits/:commit_sha
				try {
					sendGet("https://api.github.com/repos/eclipse/che/commits/" + sha);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void sendGet(String url) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		
		FileWriter writer = new FileWriter("commits.txt", true);
		BufferedWriter bw = new BufferedWriter(writer);

		while ((inputLine = in.readLine()) != null) {
			bw.write(inputLine);
		}
		in.close();
		bw.close();
	}
}
