package recipes.dao;

public class FsDAOFactory extends DAOFactory {

    @Override
    public RecipeDAO getRecipeDAO() {
        return new FsRecipeDAO();
    }

}
