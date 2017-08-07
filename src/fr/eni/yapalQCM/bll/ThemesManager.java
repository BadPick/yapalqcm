package fr.eni.yapalQCM.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.yapalQCM.bo.Question;
import fr.eni.yapalQCM.bo.Test;
import fr.eni.yapalQCM.bo.Theme;
import fr.eni.yapalQCM.dal.QuestionDAL;
import fr.eni.yapalQCM.dal.TestDAL;
import fr.eni.yapalQCM.dal.ThemeDAL;


public class ThemesManager {
	static ThemeDAL themeDal = new ThemeDAL();
	public static List<Theme> getThemes() throws SQLException{
		List<Theme> listeThemes = null;
		
		
		listeThemes=themeDal.getAll();
		if(listeThemes.size()==0){
			listeThemes=null;
		}
		return listeThemes;
	}

	public static List<Question> listeQuestions(String id) throws SQLException {
		List<Question> liste = new ArrayList<Question>();
		Theme theme = new Theme();		
		theme.setId(Integer.parseInt(id));
		QuestionDAL questionDal = new QuestionDAL();
		List<Integer> listeIdQuestion = themeDal.listeIdQuestions(theme);
		
		for (Integer idQuestion : listeIdQuestion) {
			Question question = new Question();
			question.setId(idQuestion);
			question=questionDal.getOne(question);
			liste.add(question);
		}			
		return liste;
	}

	public static void ajouterThemes(String libelle) throws SQLException {
		Theme theme = new Theme();
		theme.setNom(libelle);
		themeDal.add(theme);		
	}

	public static void modifierThemes(String id, String libelle) throws SQLException {
		Theme theme = new Theme();
		theme.setId(Integer.parseInt(id));
		theme.setNom(libelle);
		themeDal.update(theme);		
	}

	public static void supprimerTheme(String id) throws SQLException {
		Theme theme = new Theme();
		theme.setId(Integer.parseInt(id));
		themeDal.delete(theme);
	}

	public static List<Test> listeSections(String id) throws SQLException {
		List<Test> liste = new ArrayList<Test>();
		Theme theme = new Theme();		
		theme.setId(Integer.parseInt(id));
		TestDAL testDal = new TestDAL();
		List<Integer> listeIdTest = themeDal.listeIdTest(theme);
		
		for (Integer idtest : listeIdTest) {
			Test test = new Test();
			test.setId(idtest);
			test=testDal.getOne(test);
			liste.add(test);
		}			
		return liste;
	}

}
