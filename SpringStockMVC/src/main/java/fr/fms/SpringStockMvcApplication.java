package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class SpringStockMvcApplication implements CommandLineRunner {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;


	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		/*articleRepository.save(new Article("Samsung ", 250));
		articleRepository.save(new Article("hp ", 280));
		articleRepository.save(new Article("Asus ", 270));
		articleRepository.save(new Article("dell ", 260));*/
		//articleRepository.save(new Article(null,"Hp253Futur ", 259));
		//articleRepository.findAll().forEach(a -> System.out.println(a));
//		categoryRepository.save(new Category("Ordinateur"));

//		Category Ordinateur = categoryRepository.save(new Category("Ordinateur"));
//		Category smartphone = categoryRepository.save(new Category("Smartphone"));
//		Category Tablette = categoryRepository.save(new Category("Tablette"));
//
//
//		articleRepository.save(new Article( "Samsung ", "S10" ,250, smartphone));
//		articleRepository.save(new Article( "Samsung ", "S9" ,350,smartphone));
//		articleRepository.save(new Article( "Xiaomi ", "MI12" ,350, smartphone));
//		articleRepository.save(new Article( "Apple ", "Iphone142", 950,smartphone));
//		articleRepository.save(new Article( "Apple ", "Iphone2047",1999, smartphone));
//
//		articleRepository.save(new Article("hp ", "EliteBook", 650,Ordinateur));
//		articleRepository.save(new Article("Asus ", "R512", 980, Ordinateur));
//		articleRepository.save(new Article("Toshiba ", "Ma1452", 650, Ordinateur));
//		articleRepository.save(new Article("Apple ", "Mac251", 1000, Ordinateur));
//		articleRepository.save(new Article("hp ", "EliteMac", 650, Ordinateur));
//
//		articleRepository.save(new Article("Apple","Ipad66",  350, Tablette));
//		articleRepository.save(new Article("Samsung","GalaxyTab",  450, Tablette));
//		articleRepository.save(new Article("Apple","Ipad560",  698, Tablette));
//		articleRepository.save(new Article("Asus","Tab43",  350, Tablette));
//		articleRepository.save(new Article("Samsung","GalaxyTab10",  420, Tablette));



	}
}
