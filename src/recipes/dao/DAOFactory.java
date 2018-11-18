package recipes.dao;

abstract public class DAOFactory {

    public static DAOFactory getDAOFactory(String type) throws IllegalAccessException {
        if (type.equalsIgnoreCase("FileSystem")) {
            return new FsDAOFactory();
        } else {
            throw new IllegalAccessException("Unknown Factory Type!");
        }
    }

    public abstract RecipeDAO getRecipeDAO();
}
