package dao;

import model.Pessoa;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDao {
    private File arquivo;

    public PessoaDao() {
        arquivo = new File("pessoas.ser");

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    public Set<Pessoa> getPessoas(){
        if(arquivo.length()>0){
            try{
                FileInputStream inputStream = new FileInputStream(arquivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Pessoa> pessoas = (Set<Pessoa>) objectInputStream.readObject();
                return pessoas;
            }
            catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            }
            catch (IOException e) {
                System.out.println("Falha ao ler arquivo");
            }
            catch (ClassNotFoundException e) {
                System.out.println("Falha ao ler arquivo");
            }
        }
        return new HashSet<>();
    }

    public boolean salvar(Pessoa Pessoa) {
        Set<Pessoa> Pessoas = getPessoas();
        if(Pessoas.add(Pessoa)){
            try{
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(Pessoas);
                return true;
            }
            catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            }
            catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    public boolean deletar(Pessoa Pessoa) {
        Set<Pessoa> Pessoas = getPessoas();
        if(Pessoas.remove(Pessoa)){
            try{
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(Pessoas);
                return true;
            }
            catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            }
            catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    public Pessoa buscarPorEmail(String email){
        Set<Pessoa> Pessoas = getPessoas();
        for(Pessoa Pessoa : Pessoas){
            if(Pessoa.getEmail().equals(email)) return Pessoa;
        }
        return null;
    }
}

