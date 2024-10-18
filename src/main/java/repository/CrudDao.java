package repository;

import javafx.collections.ObservableList;

public interface CrudDao <T> extends SuperDao {
    boolean save(T t);
    ObservableList<T> getAll();


}
