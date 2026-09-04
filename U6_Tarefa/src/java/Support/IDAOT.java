package Support;

import java.util.ArrayList;

// Utiliza Generics como tipo de dado

public interface IDAOT<T> {
    public boolean Insert(T entity);

    public boolean Update(T entity);

    public boolean Delete(int id);

    public ArrayList<T> GetAll();

    public ArrayList<T> GetByValue(String criterio, String valor);

    public T GetById(int id);
}
