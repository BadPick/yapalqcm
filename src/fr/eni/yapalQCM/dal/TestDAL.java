/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.yapalQCM.bo.Test;

/**
 * @author wvignoles2017
 * @date 31 juil. 2017
 * @version yapalqcm V1.0
 */
public class TestDAL implements IDAL<Test> {

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getLength(java.lang.String)
	 */
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getOne(int, java.lang.String)
	 */
	@Override
	public Test getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll(java.lang.String)
	 */
	@Override
	public List<Test> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean add(Test t) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean update(Test t) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int, java.lang.String)
	 */
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Test itemBuilder(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
