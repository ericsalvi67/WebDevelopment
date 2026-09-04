package Support;

import java.util.ArrayList;

// Utiliza Generics como tipo de dado

public interface IDAOT<T> {
    public String salvar(T entity);

    public String atualizar(T entity);

    public String excluir(int id);

    public ArrayList<T> consultarTodos();

    public ArrayList<T> consultar(String criterio, String valor);

    public T consultarId(int id);
}
