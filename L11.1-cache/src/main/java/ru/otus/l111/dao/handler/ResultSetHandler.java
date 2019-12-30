package ru.otus.l111.dao.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface ResultSetHandler<T> {

	List<T> handle(ResultSet rs) throws SQLException;

}