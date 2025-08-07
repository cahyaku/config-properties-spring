# Spring Configuration Properties Section-2.

#

## Resource

```
Resource adalah bagian untuk mengakses file atau resource lain yang ada di dalam classpath atau filesystem. 
Resource dapat berupa file, URL, atau input stream.
Caranya gunakan class turunan dari `org.springframework.core.io.Resource` seperti `FileSystemResource`, 
`ClassPathResource`, atau `UrlResource`.
```

> > Contoh: pada class ResourceTest
> > ClassPathResource untuk mencari file yang ada di dalam project kita.
> > Resource diperlukan saat melakukan config properties untuk menentukan lokasi file yang akan digunakan.

## Resource Loader

```
Resousrce Loader digunakan untuk mengambil data secara otomatis tanpa membuat object resourcenya.
Caranya menambagkan prefixnya, misal:
1. classpath:/com/pzn/application.properties
2. file:/home/pzn/application.properties
3. url:http://localhost:8080/application.properties

Keterangan:
1. classpath: untuk mengambil file dari classpath.
2. file: untuk mengambil file dari filesystem.
3. url: untuk mengambil file dari URL.
```

> Pertanyaan: Resource loader adalah sebuah interface, sehingga kita perlu mengimplementasikannya.
> Jawaban: Tidak perlu, karena Spring sudah menyediakan implementasi dari Resource Loader.
> Seperti applicationContext adalah turunan dari Resource Loader.
>> Contoh: pada package resourceloader -> class ResourceLoaderTest
> > file yang diambil adalah resource.txt dalam folder text.

## Message Source

```
- Pada Java dapat mengambil data dengan menggunakan properties (udemy-section1).
- Dalam Spring GUNAKAN fitur Message Source untuk mengambil message dari resource.
- JADI lokasinya tetap menggunakan resource.

Message Source adalah sebuah interface, untuk menggunakannya kita perlu implementasi class-nya.
UTUNGNYA Spring sudah menyediakan implementasi dari Message Source, yaitu `ResourceBundleMessageSource`.
JADI kita tidak perlu membuatnya secara manual (mengimplementasikan sendiri).
```

> > Contoh: Kita akan membuat file my.properties di dalam folder resources.
> > Misalnya buat file yang bahasa indonesia my in ID.properties
> > Kemudian coba dengan messageSourcenya pada package messagesource -> class MessageSourceTest
>>> Caranya:
> > 1. Buat file `my.properties` di dalam folder resources.
> > 2. Buat file `my_id.properties` untuk bahasa Indonesia.

## Spring Boot Message Source

```
- Pada spring boot tidak perlu membuat Message Source secara manual.
- Secara default Spring Boot akan membuat Message Source dengan mengambil data resource dari file
  `messages.properties` yang ada di dalam classpath. (Jadi sebelumnya kan manual pakai my.properties).
 - Untuk mendapatkan Message Source kita juga dapat menggunakan MessageSourceAware.
```

> Caranya:
> Format: `messages_{locale}.properties`
> > 1. Buat file `messages.properties` di dalam folder resources.
> > 2. Buat file `messages_id.properties` untuk bahasa Indonesia.
>>> Contoh: Kemudian coba gunakan messageSourcenya.
> > > pada package springbootmessagesource -> class SpringBootMessageSourceTest

## Application Properties

```
Jika membuat project Spring menggunakan start.spring.io, otomatis terdapat file `application.properties`.
File ini adalah pusat konfigurasi untuk aplikasi Spring Boot.
```

> Dalam file `application.properties`  dapat membuat konfigurasi apapun contoh:
> application.name=Belajar Spring Boot

> Bagiamana cara mengaksesnya?
> 1. Cara paling sederhana => menggunakan Environment.
> > Contoh: pada package appproperties -> class ApplicationPropertiesTest

## Environment

```
- Environment digunakan untuk mengakses data di dalam Application Properties.
- TETAPI Ecnvironment dapat digunakan untuk mengakses data 
environtment variable pada sistem operasi.
- EnvirontmentAware dapat digunakan untuk mendapatkan object Environment.
```

> > Contoh: pada package environment (test)-> class EnvironmentTest
> > running di terminal: env | grep JAVA_HOME, ini hasilnya masih null
> > untuk tau java homenya bisa running: readlink -f $(which java) pada terminal

## Annotation @Value

```
- Annotation @Value digunakan untuk melakukan inject dari properties ke field yang kita tandai.
- Jadi ini digunakan untuk mengambil data dari Application Properties.
- Cara yakni:
  menggunakan kode ${nama.properties.nya}
  Secara otomatis akan diambil valuenya, dan langsung melakukan konversi juga.
```

> > Contohnya:
> > update application.properties: application.name, application.version, dan application.production-mode
> > Kemudian new package value -> class ValueTest

```
Selain untuk mengambil data dari Application Properties,
@Value juga digunakan untuk mengambil data dari system properties atau environment variable.
Contohnya untuk mengambil data dari environment variable JAVA_HOME, juga ada pada class ValueTest.
```

## Properties Source

```
Secara default, application properties hanya akan mengambil dari file application.properties yang ada di dalam classpath.
```

> > Tapi bagaimana jika kita ingin mengambil dari file lain?
> > Contoh: menambahkan sample.properties di dalam folder resources.
> > Cara mengambilnya:
> > Tambahkan @PropertySource pada class yang akan mengambil data dari file tersebut.
> > Kemudian buat new package propertiessource -> class PropertySourceTest

## Test Properties Source

```
Saat membuat unit test, kadang kita ingin mengakses properties file yang berbeda untuk mencoba skenario yang berbeda.
Ini akan sulit jika menggunakan @PropertySource.
JADI kita dapat menggunakan @TestPropertySource.
```

> > Contoh: membuat properties di folder test, jadi bukan di main resources.
> > 1. Buat folder resources di dalam folder test.
> > 2. Buat file test.properties di dalam folder resources.
> > 3. Buat package baru testpropertysource -> class TestPropertySourceTest
> > 3. Kemudian pakai @TestPropertySource pada class yang akan mengakses file tersebut.
> > 4. Ini pada class TestApplication

## Profile

```
- Profile adalah fitur untuk menentukan COMPONENT mana yang mau jalan.
- Profile cocok ketika butuh component berbeda pada kondisi tertentu, misalnya:
  Membuat component untuk koneksi ke Memory Database, tapi jika di Local misal, kita ingin
  componentnya diganti dengan koneksi di memory aplikasi saja.
- Untuk menandai sebuah komponen dengan informasi profile, kita dapat menggunakan annotation @Profile.
``` 

> > Contohnya: tambahkan pada application.properties
> > spring.profiles.active=local
> > JIKA mau lebih dari 1 tinggal tambahkan koma, misal: spring.profiles.active=local, dev, test

#### Profile Properties

```
- Untuk menentukan profile mana yang akan berjalan, kita bisa menentukan di application.properties]
  dengan menggunakan key spring.profiles.active.
- Dimana kita bisa menentukan active profile lebih dari satu.
```

> > Contoh: pada application.properties tambahkan spring.profiles.active=local
> > Kemudian saat membuat @Component, kita bisa menambahkan annotation @Profile("local").
> > pada class ProfileTest di package profile.

#### Active Profile annotation

```
- Mengubah profile di application properties akan menyulitkan ketika membuat unit test untuk BEBERAPA profile.
- Untuk mengubah profile di unit test, kita bisa menggunakan annotation ActiveProfiles.
- JADI Aktif Profile bise digunakan untuk mengubah-ubah profile yang saat ini di jalankan.

KESIMPULAN:
profile bisa lebih dari satu, jadi bisa mengaktifkan beberapa profile sekaligus.
```

> > Contoh: pada package profile -> class ProfileTest (paling atas).

#### Profile di Environment

```
- Untuk mendapatkan profile pada saat aplikasi berjalan, 
  kita bisa menggunakan Environment.
- Dalam environment terdapat method getActiveProfiles() untuk mendapatkan profile yang aktif.
```

> > Contoh: pada package profileenvironment -> class ProfileEnvironmentTest

## Profile Properties File

```
- Dengan fitur profile, kita bisa membuat file properties yang sesuai dengan profile yang aktif.
- Penamaan properties file adalah application-{profile}.properties.
- Misal active profile adalah dev, maka application-dev.properties akan digunakan.
```

> > Contoh: pada folder main -> resources buat file:
> > 1. application-production.properties
> > 2. application-test.properties
       > > Misalnya akan membuat beberapa profile, misalnya default, production, dan test.
       > > Jadi tambahkan profile.default pada application.properties
       > > Buat komponen, pada folder test -> package profileproperties -> class ProfilePropertiesTest

## Configuration Properties

```
- Fitur ini bisa digunakan untuk melakukan binding secara otomatis key yang ada di 
  application.properties ke Java Bean property secara otomatis.
- Binding adalah proses menghubungkan data dari file properties ke dalam Java Bean, 
  atau menyamakan data dari file properties ke dalam Java Bean.
- Tapi untuk menggunakan fitur ini, kita perlu menambahkan dependency 
  spring-boot-configuration-processor pada file pom.xml.

CARANYA:
- Untuk menandai Java Bean agar otomatis di binding ke Application Properties,
  kita bisa menggunakan annotation @ConfigurationProperties.
- Kemudian perlu menambahkan prefix untuk key di application.propertiesnya.
```

> > Contoh: Class ApplicationProperties pada package properties -> main.
> > Tapi @ConfigurationProperties tidak akan bekerja jika tidak ada prefixnya dan tidak otomatis melakukan binding.
> > Jadi perlu membuat metadata untuk bindingnya. Cara run di terminal: mvn clean compile.
> > Kemudian cek di target/classes/META-INF/spring-configuration-metadata.json
> > Tapi untuk menjalankannya,
> > kita perlu menambahkan annotation @EnableConfigurationProperties pada class yang akan menggunakan binding.
> > Contoh: pada folder test -> package configurationproperties -> class ConfigurationPropertiesTest

> > > Jadi nanti bisa pakai semua properties yang ada di spring-configuration-metadata.json

## Complex Configuration Properties

```
- Configuration Properties mendukung Java Bean yang kompleks.
- Misalnya yang berisikan Java Bean object lain.
- Ini memungkinkan pembuatan Configuration properties menjadi lebih mudah,
  karena tidak perlu dilakukan secara manual.
```

> Misalnya kita memiliki DatabaseProperties yang memiliki object kompleks.
> Kita coba:
> > 1. Buat class DatabaseProperties, saya gabung 1 file saja di class ApplicationProperties -> package properties ->
       main.
> > 2. Jadi ini perlu di compile ulang, karena ada perubahan di class ApplicationProperties => run = mvn clean compile.
> > 3. Kemudian cek di target/classes/META-INF/spring-configuration-metadata.json
> > 4. Kemudian tambahkan pada apllication.properties, untuk databasenya:
       > > meliputi password, username, dan url.
> > 5. Kemudian update unit testnya di class ConfigurationPropertiesTest