# Password Manager
  First OOP project, contributors Anneli Oro, Mari Lee Lumberg, Karel Konga
  
## Contents
### Description
  Final working project is located under paroolihaldur_fx. Under that is src/main/main.java which in order to test needs to be run. This repo has a lot of unnecessary files due to the lack of knowledge of what is needed for a java project to run and unfamiliarity with IntelliJ IDE at the time and lost interest in the project after passing the course. This project serves no real life usage but it served it's purpose as a learning experience for us as programmers and as a way to practice proper group work on a programming project.
  
### Login page

The login page of the password manager that launches first when the user runs the program. From here the user can create a new account or log into their existing account. 

  <img width="604" height="435" alt="image" src="https://github.com/user-attachments/assets/d7325e6a-3d7c-485e-90ef-73012cec52bc" />

#### Error messages

Program presents the user with error messages when the password or username don't match or such an account does not yet exist.

  <img width="287" height="200" alt="image" src="https://github.com/user-attachments/assets/51b1e38d-8965-4b75-ac8f-3b935316f691" />
  <img width="286" height="218" alt="image" src="https://github.com/user-attachments/assets/a8a67579-8ca0-4f15-b5fa-6396df6124a5" />

### Creating a new account

On this page the user can create an account by entering their username and confirming their password.

  <img width="599" height="425" alt="image" src="https://github.com/user-attachments/assets/b2da6e45-12f6-47cd-a812-9b5b42d938bc" />
  
#### Error messages 2

In the case of empty fields or inserting already used information, the user is presented with error messages.

  <img width="291" height="244" alt="image" src="https://github.com/user-attachments/assets/78c05970-970e-4b07-9a31-8b2b3b121faa" />
  <img width="309" height="268" alt="image" src="https://github.com/user-attachments/assets/a3289198-403a-480a-a2a2-f77e0215a978" />

### Main page

On the main page the user can navigate between tabs (located on the left) such as: "Paroolid(passwords)"; "Lisa uus(add new)"; "Genereeri(generate)"; "Logi välja(log out)"; "Lülita välja(shut down). On the right the user can click on the category/source of the passwords they previously saved and see the respective info under that. 

  <img width="696" height="428" alt="image" src="https://github.com/user-attachments/assets/0e48ade9-f49a-4dcc-8671-e08bd5a0c8aa" />

#### Category password(s)
  <img width="463" height="156" alt="image" src="https://github.com/user-attachments/assets/86f31e69-5830-4751-bc53-036a297a7654" />
  <img width="445" height="177" alt="image" src="https://github.com/user-attachments/assets/dd3d998d-5930-4894-bc98-66cd2eeb9c95" />

#### Changing password & deleting password

The user can change ("muuda") or delete ("kustuta") their passwords. Both options prompt the user with a new window that either instructs them to change the attributes or ask for confirmation.

  <img width="398" height="209" alt="image" src="https://github.com/user-attachments/assets/f681f966-d291-4062-be54-90c47c1dc612" />
  <img width="364" height="197" alt="image" src="https://github.com/user-attachments/assets/659a74ea-7ff3-4ac4-9274-bdc37e74b92a" />

### Add new page

On the add new page the user is able to create a new password by entering the source of the password ("allikas"), username ("kasutajanimi") and password ("parool").

  <img width="692" height="423" alt="image" src="https://github.com/user-attachments/assets/c33c1c75-e91a-41cf-a713-10c539317a65" />

### Generate page

On the generate page is a simple password generating tool that the user can use to generate a password of minimum 4 characters and maximum of 32 characters. This minimum is set at 4 in order to have at least 1 capital-, 1 lowercase letter, 1 number and 1 symbol. The simple and naive approach of it can be seen under `paroolihaldur_fx/src/main/ParooliGeneraator.java`.

  <img width="692" height="419" alt="image" src="https://github.com/user-attachments/assets/1cfcda83-f29d-4fa4-b067-ab4b213e5ad4" />

### Program window and UI

The UI of this program was made using SceneBuilder and JavaFX. All parts of the UI react to mouse hover and can be navigated through with the use of `TAB` key for accessibility. 

### Behind the scenes

Due to the focus of the project and lack of knowledge, time and skills at the time of making this, the approach used for saving and crypting the passwords is quite naive and not secure. Namely the program saves all passwords localy in a ´.txt´ file per user, using caesar cipher to encrypt and decrypt the passwords in the file. This can be seen in `paroolihaldur_fx/src/main/Krüpteerimine.java`
