/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.yapalQCM.bo.Question;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class QuestionDAL implements IDAL<Question> {

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength()
	 */
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int)
	 */
	@Override
	public Question getOne(Question q) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Question q) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Question q) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Question q) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Question itemBuilder(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
