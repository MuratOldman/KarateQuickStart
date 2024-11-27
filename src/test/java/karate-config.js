function fn() {
   var env = karate.env || 'dev2'; // Varsayılan ortam: dev2
       karate.log('Karate environment:', env);

       // db.properties dosyasını yükle
       var properties = read('classpath:database.properties');
    var config = {
            env: env,
//            db: {
//                url: properties[env + '.db.url'],
//                user: properties[env + '.db.user'],
//                password: properties[env + '.db.password']
//            },
        baseUrl: 'https://' + env + '.thehartford/api/user'
    };


    karate.log('Base URL:', config.baseUrl);
//    karate.log('user:', config.db.user);
//    karate.log('password:', config.db.password);
//    karate.log('Database URL:', properties[env + '.db.url']);
//    karate.log('Database User:', properties[env + '.db.user']);
//    karate.log('Database Password:', properties[env + '.db.password']);

    return config;
}



