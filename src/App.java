import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.transform.Source;

import model.Contact;

public class App {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        afficherMenu();
        while (true) {
            String choix = scan.nextLine();
            switch (choix) {
                case "1":
                    ajouterContact();
                    break;
                case "2":
                    listerContact();
                    break;
                case "3":
                    removeContact();
                    break;
                case "4":
                    editContact();
                    break;
                case "q":
                    scan.close();
                    return;
                default:
                    System.out.println("Boulet!!!!");
                    break;
            }
            afficherMenu();
        }
    }

    private static void listerContact() {
        // Contact c = new Contact();
        try {
            ArrayList<Contact> liste = Contact.lister();

            for (Contact contact : liste) {
                System.out.println(contact.getPrenom() + " " + contact.getNom());
            }
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier");
        }

    }

    private static void removeContact() {
        try {
            System.out.println("Saisissez le numero de téléphone que vous voulez supprimer.");
            ArrayList<Contact> liste = Contact.lister();
            System.out.println(liste);
            String numberToDelete = scan.nextLine();
            for (Contact contact : liste) {
                if (contact.getNumero().equals(numberToDelete)) {
                    liste.remove(contact);
                    Contact.clearFile();
                    Contact.saver(liste);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void editContact() {
        try {
            System.out.println("Saisissez le numero de téléphone que vous voulez modifier.");
            ArrayList<Contact> liste = Contact.lister();
            System.out.println(liste);
            String numberToEdit = scan.nextLine();

            for (Contact contact : liste) {
                if (contact.getNumero().equals(numberToEdit)) {

                    System.out.println("Saisir le nom");
                    contact.setNom(scan.nextLine());
                    System.out.println("Saisir le prenom");
                    contact.setPrenom(scan.nextLine());
                    System.out.println("Saisir le numero");
                    contact.setNumero(scan.nextLine());
                    System.out.println("Saisir le mail");
                    contact.setMail(scan.nextLine());
                    System.out.println("Saisir la date de naissance");
                    contact.setDateNaissance(scan.nextLine());

                    Contact.clearFile();
                    Contact.saver(liste);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void ajouterContact() {

        Contact c = new Contact();
        System.out.println("Saisir le nom:");
        c.setNom(scan.nextLine());
        System.out.println("Saisir le prénom:");
        c.setPrenom(scan.nextLine());

        do {
            try {
                System.out.println("Saisir le téléphone:");
                c.setNumero(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir le mail:");
                c.setMail(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir la date de naissance:");
                c.setDateNaissance(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Error, try again!");
            }
        } while (true);

        c.enregistrer();

    }

    public static void afficherMenu() {
        // 1
        /*
         * System.out.println("-- MENU --");
         * System.out.println("1- Ajouter un contact");
         * System.out.println("2- Lister les contacts");
         * System.out.println("q- Quitter");
         */
        // 2
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("3- Supprimer un contact");
        menus.add("4- Modifier un contact");
        menus.add("q- Quitter");
        for (String s : menus) {
            System.out.println(s);
        }
    }
}
