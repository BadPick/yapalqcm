/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
package fr.eni.yapalQCM.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.yapalQCM.bo.Inscription;

/**
 * @author wvignoles2017
 * @date 1 août 2017
 * @version yapalqcm V1.0
 */
public class InscriptionDAL implements IDAL<Inscription> {

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
	public Inscription getOne(Inscription i) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#getAll()
	 */
	@Override
	public List<Inscription> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#add(java.lang.Object)
	 */
	@Override
	public boolean add(Inscription t) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#update(java.lang.Object)
	 */
	@Override
	public boolean update(Inscription i) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#delete(int)
	 */
	@Override
	public boolean delete(Inscription i) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * {@inheritDoc}
	 * @see fr.eni.yapalQCM.dal.IDAL#itemBuilder(java.sql.ResultSet)
	 */
	@Override
	public Inscription itemBuilder(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
