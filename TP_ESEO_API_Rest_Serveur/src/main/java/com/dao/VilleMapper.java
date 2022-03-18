package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VilleMapper implements RowMapper<String> {

	public String mapRow(ResultSet resultSet, int i) throws SQLException {

		return resultSet.getString("Nom_commune");
	}
}