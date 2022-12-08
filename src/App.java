import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

import javax.xml.transform.Source;

import model.Contact;

public class App {
    private static Scanner scan = new Scanner(System.in);

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
            System.out.println("Saisir le mail de la personne que vous voulez supprimer..");

            ArrayList<Contact> liste = Contact.lister();
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
            ArrayList<Contact> liste = Contact.lister();

            System.out.println("Saisir le mail de la personne à modifier.");
            String mailToEdit = scan.nextLine();

            for (Contact contact : liste) {
                if (contact.getMail().equals(mailToEdit)) {

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

    private static void sortByName() {
        try {
            ArrayList<Contact> liste = Contact.lister();

            Collections.sort(liste, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getNom().compareTo(c2.getNom());
                }
            });

            Contact.clearFile();
            for (Contact contact : liste) {
                contact.enregistrer();
            }
        } catch (IOException e) {
            System.out.println("Error :" + e);
        }
    }

    private static void sortByDate() {
        try {
            ArrayList<Contact> liste = Contact.lister();

            Collections.sort(liste, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    return c1.getDateNaissance().compareTo(c2.getDateNaissance());
                }
            });

            Contact.clearFile();
            for (Contact contact : liste) {
                contact.enregistrer();
            }
        } catch (IOException e) {
            System.out.println("Error :" + e);
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

    public static void searchByName() {
        try {
            ArrayList<Contact> liste = Contact.lister();

            System.out.println("Saisir le nom de la personne a recherché");
            String contactToSearch = scan.nextLine();

            for (Contact contact : liste) {
                if (contact.getNom().equals(contactToSearch)) {
                    System.out.println(contact.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        menus.add("5- Trier par noms");
        menus.add("6- Trier par date de naissance");
        menus.add("7- Chercher un contact par son nom");
        menus.add("q- Quitter");
        for (String s : menus) {
            System.out.println(s);
        }
    }

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
                case "5":
                    sortByName();
                    break;
                case "6":
                    sortByDate();
                    break;
                case "7":
                    searchByName();
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

}
