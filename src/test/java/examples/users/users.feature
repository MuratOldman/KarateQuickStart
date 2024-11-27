Feature: sample karate test script
  for help, see: https://github.com/karatelabs/karate/wiki/IDE-Support

  Background:
    * url 'https://jsonplaceholder.typicode.com'

    @TestOne
  Scenario: get all users and then get the first user by id
    Given path 'users'
    When method get
    Then status 200
    * def first = response[0]

    Given path 'users', first.id
    When method get
    Then status 200

  @Test1
  Scenario: change environment
    Given set karate.config.endpoint = 'employee'
    When print baseUrl

  @Test2
  Scenario: Test with dynamic endpoint and parameters
      # Java sınıfı ile veritabanı yapılandırmasını yükle
    * def connection = Java.type('utils.DatabaseUtils').createConnection(karate.env)
    #* def dbInstance = new dbUtils(karate.env)

    # Veritabanı yapılandırmalarını logla
#    * print 'Database URL:', getDbUrl()
#    * print 'Database User:', getDbUser()
#    * print 'Database Password:', getDbPassword()

    # Endpoint ve parametreleri tanımlayın
    * def endpoint = 'employee'
    * def employeeId = '123'
    * def userName = 'john_doe'

    # Karate-config.js'den baseUrl al
    #* def baseUrl = karate.config.baseUrl
    * print baseUrl

    # Dinamik URL oluşturmak için Java fonksiyonunu çağır
    * def dynamicUrl = Java.type('utils.DynamicUrl').getDynamicUrl(baseUrl, endpoint, employeeId, userName)

    # Log ile kontrol edin
    * print 'Dynamic URL:', dynamicUrl



  Scenario: create a user and then get it by id
    * def user =
      """
      {
        "name": "Test User",
        "username": "testuser",
        "email": "test@user.com",
        "address": {
          "street": "Has No Name",
          "suite": "Apt. 123",
          "city": "Electri",
          "zipcode": "54321-6789"
        }
      }
      """

    Given url 'https://jsonplaceholder.typicode.com/users'
    And request user
    When method post
    Then status 201

    * def id = response.id
    * print 'created id is: ', id

    Given path id
    # When method get
    # Then status 200
    # And match response contains user
  