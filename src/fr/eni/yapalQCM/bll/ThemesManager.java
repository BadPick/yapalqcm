package fr.eni.yapalQCM.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.yapalQCM.bo.Theme;
import fr.eni.yapalQCM.dal.ThemeDAL;

public class ThemesManager {
	
	public static List<Theme> getThemes() throws SQLException{
		List<Theme> listeThemes = null;
		
		ThemeDAL themeDal = new ThemeDAL();
		listeThemes=themeDal.getAll();
		if(listeThemes.size()==0){
			listeThemes=null;
		}
		return listeThemes;
	}

}
