package dao;

import java.util.List;

public interface IDAO<T> {
	boolean insert(T t);
	boolean update(String id, String ... newdata);
	boolean delete(String id);
	T get(String id);
	T get(int id);
	List<T> getAll();
}
