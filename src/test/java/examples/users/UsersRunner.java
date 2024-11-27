package examples.users;

import com.intuit.karate.junit5.Karate;

class UsersRunner {
    
    @Karate.Test
    Karate testUsers() {
        System.setProperty("karate.env", "dev2");
        return Karate.run("users")
                .tags("@Test2")
                .relativeTo(getClass());
    }    

}
