# UTS Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: {Nada Ismaya}</li>
  <li>NIM: {23552011125}</li>
  <li>Studi Kasus: {Membuat Aplikasi ToDo - List}</li>
</ul>

## Judul Studi Kasus
<p>Aplikasi Manajemen Tugas Pribadi (Todo List) dengan Fitur Login Berbasis Spring Boot & Thymeleaf
</p>

## Penjelasan Studi Kasus
<p>Aplikasi ini memungkinkan pengguna untuk melakukan registrasi, login, dan mengelola daftar tugas harian mereka. Setiap pengguna memiliki daftar todo-nya masing-masing. Tugas dapat ditambahkan, diperbarui, ditandai selesai/belum selesai, dan dihapus. Aplikasi ini juga menunjukkan implementasi dari 4 pilar OOP dalam pengembangannya.
</p>

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Inheritance memungkinkan sebuah class (anak) mewarisi properti dan metode dari class lain (induk), sehingga kita bisa membangun class baru tanpa menulis ulang kode dari class yang sudah ada.
Dalam proyek ini, CustomUserDetails menerapkan (implements) interface UserDetails, yang merupakan bagian dari Spring Security. Dengan begitu, CustomUserDetails mewarisi kontrak fungsi-fungsi yang dibutuhkan untuk otentikasi, seperti getUsername(), getPassword(), dan getAuthorities().

Contoh:
public class CustomUserDetails implements UserDetails {
    // Inherits methods from UserDetails
}
</p>

### 2. Encapsulation
<p>Encapsulation menyembunyikan detail implementasi sebuah class dari luar dan hanya memperbolehkan akses melalui metode getter dan setter. Pada Proyek ini, Class seperti User dan ToDo menggunakan akses modifier private untuk atribut dan menyediakan getter/setter publik untuk mengakses dan memodifikasi data tersebut.

Contoh pada class User:
private String username;
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}
</p>

### 3. Polymorphism
<p>Polymorphism memungkinkan objek untuk memiliki banyak bentuk. Ini bisa berupa method overriding (dalam inheritance) atau method overloading (dalam satu class). Pada proyek ini, UserDetailsService adalah interface yang diimplementasikan oleh CustomUserDetailsService. Karena itu, Spring bisa memanggil loadUserByUsername() dari CustomUserDetailsService melalui referensi ke UserDetailsService.

Contoh:
UserDetailsService service = new CustomUserDetailsService();
service.loadUserByUsername("user");

ToDoService memiliki beberapa method findBy..., semuanya menggunakan nama yang mirip tetapi berperilaku berbeda tergantung parameter, contohnya:
findCompletedByUser(User user)
findIncompleteByUser(User user)
</p>

### 4. Abstract
<p>Abstraction menyembunyikan kompleksitas internal dan hanya menampilkan antarmuka yang diperlukan kepada pengguna. Ini bisa dicapai melalui abstract class atau interface. Pada proyek ini, Interface seperti UserDetails, UserDetailsService, dan JpaRepository adalah bentuk abstraksi. Kita hanya mengimplementasikan fungsi-fungsi pentingnya saja, tanpa tahu detail internal library Spring.

Contoh: 
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

Spring secara otomatis mengimplementasikan interface JpaRepository di belakang layar—developer tidak perlu tahu bagaimana query SQL sebenarnya dijalankan.
</p>

## Demo Proyek
<ul>
  <li>Github: <a href="https://github.com/nadaismaya/UTS_PBO2_TIF-K-23A_23552011125.git">Github</a></li>
  <li>Youtube: <a href="https://drive.google.com/file/d/1-4Fnyat8XQrj20xJH4UMdpW9MVIJQbjV/view?usp=sharing">Drive</a></li>
</ul>
