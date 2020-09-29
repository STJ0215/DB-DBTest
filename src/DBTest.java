import java.util.List;
import java.util.Scanner;


public class DBTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Dao dao = new Dao();
		//String cmd = "";
		
		
		while (true) {
			System.out.print("명령어를 입력해 주세요 (? = help): ");
			String cmd = sc.nextLine();
			
			
			if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				
				break;
			}
			else if (cmd.equals("help")) {
				System.out.println("list   : 게시물 목록");
				System.out.println("read   : 게시물 조회");
				System.out.println("add    : 게시물 추가");
				System.out.println("update : 게시물 수정");
				System.out.println("delete : 게시물 삭제");
				System.out.println("exit   : 프로그램 종료");
			}
			else if (cmd.equals("list")) {
				List<Article> articles = dao.getAllArticles();
				
				for (Article article : articles) {
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("내용 : " + article.getBody());
					System.out.println("작성자 : " + article.getNickname());
					System.out.println("조회수 : " + article.getHit());
					//System.out.println("작성일 : " + article.getRegDate());
				}
			}
			else if (cmd.equals("read")) {
				System.out.print("번호를 입력해 주세요 : ");
				int id = sc.nextInt();
				
				dao.printArticle(id);
			}
			else if (cmd.equals("add")) {
				System.out.print("제목을 입력해 주세요 : ");
				String title = sc.nextLine();
				System.out.print("내용을 입력해 주세요 : ");
				String body = sc.nextLine();
				
				dao.insertArticle(title, body);
			}
			else if (cmd.equals("update")) {
				System.out.print("번호를 입력해 주세요 : ");
				int id = sc.nextInt();
				System.out.print("새로운 제목을 입력해 주세요 : ");
				String title = sc.nextLine();
				System.out.print("새로운 내용을 입력해 주세요 : ");
				String body = sc.nextLine();
				
				dao.updateArticle(id, title, body);
			}
			else if (cmd.equals("delete")) {
				System.out.print("번호를 입력해 주세요 : ");
				int id = sc.nextInt();
				
				dao.deleteArticle(id);
			}
			/*
			else {
				System.out.println("잘못된 명령어 입니다.");
			}
			*/
		}
		
	}
	
}
	