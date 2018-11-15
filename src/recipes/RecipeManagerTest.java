package recipes;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class RecipeManagerTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }


    @org.junit.Test
    public void getRecipes() {
    }

    @org.junit.Test
    public void loadRecipes() {
    }

    @org.junit.Test
    public void getRecipe() {
    }

    @org.junit.Test
    public void getEnabled() {
    }

    @org.junit.Test
    public void validateRecipes() {
    }

    @org.junit.Test
    public void executeRecipe() {
    }
}
