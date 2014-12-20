/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import View.JPrincipal;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Ruan
 */
/*

*/
public class Controladora {

    public Controladora() {
        
    }
    
    public int[][] lerArquivo(Scanner scan, List cidades, int matriz[][], int numcidades) throws FileNotFoundException, IOException{
        int aux = 0;
               
        Formatter arquivoMatriz = new Formatter("src/matriz.txt");
                
        arquivoMatriz.flush(); // CRIA OU LIMPA matriz.txt
        
        aux = scan.nextInt();
        numcidades = scan.nextInt();
        int[][] matriz2 = new int [numcidades][numcidades];
        matriz = new int [numcidades][numcidades];
        System.out.println();
        
        //pega nome das cidades
        for(int i=0;i<(numcidades);i++){
            scan.nextInt();
            scan.nextInt();
            cidades.add(scan.next());

        }
        //pega distancias
        for(int i=0;i<(numcidades);i++){
             scan.nextInt();
             scan.nextInt();
             
             for(int j=0;j<(numcidades);j++){
                matriz2[i][j] = scan.nextInt();
             }
        }
        
        /* laço para a escrita da matriz no arquivo matriz.txt*/
        int ind = 0;
        for(int i=0;i<(numcidades);i++){ 
            System.out.print(" \n");
            arquivoMatriz.format(" \n");
            for(int j=0;j<(numcidades);j++){
                System.out.print( matriz2[i][j] + " ");
                arquivoMatriz.format( matriz2[i][j] + " \t");
                
            }
        }
        System.out.println();
        matriz = matriz2;
        return matriz2;
        
    }
    public int retonaInd(String nomeCidade, List cidades){
        int ind =  cidades.indexOf(nomeCidade);
        return ind;
    }
    
    public void dijkstra(int matriz[][],int t, int s, int infinito, int tam, List cidades){
              
        int dist[] = new int [tam];        
        int perm[] = new int [tam];
        int path[] = new int [tam];
        int current /*vertice atual*/, i, k, dc /*Distancia do vertice atual(current)*/;
        int smalldist /*Menor Distancia*/, newdist /*Novo valor da distancia*/;
        
        /* Inicializa todos os índices de 'perm' como 0 e de 'dist' como INFINITY */
        for(int x = 0; x< tam; x++){
            perm[x] = 0; //perm[i] = -1
            dist[x] = infinito;
        }
        
        /* Inclui 's' em perm (perm[s]=1) e configura(armazena) sua distancia como sendo zero */
        perm[s] = 1;
        dist[s] = 0;
        
        /* define 's' como origem (fonte) da busca */
        current = s;
        k = current;
        
        while (current != t){
            smalldist = infinito;
            dc = (int) dist[current];
            for (i = 0; i < (tam); i++){
                //se o elemento NÃO está em perm
                if (perm[i] == 0){ 
                    //calcula distância a partir do vértice corrente ao vértice adjacente i
                    newdist = dc + matriz[current][i];

                    //se a distância partindo do vértice corrente for menor, atualiza o vetor
                    //de distâncias e de precedência
                    if (newdist < dist[i]){
                        dist[i] = newdist;
                        path[i] = current;
                    }

                    //determina o vértice (entre todos os não pertencentes a perm) com menor distância
                    if (dist[i] < smalldist){
                        smalldist = dist[i];
                        k = i;
                    }
                }
            } /* fim for */
        
            /* Este if garante que em caso de não existência de um caminho o programa não entre em loop infinito */
            if (current == k){
                System.out.println("\n\nCAMINHO NAO EXISTE\n\n");
                return;
            }
            current = k;
            perm[current] = 1;
        }
        /* impressao do resultado ****************/

        System.out.println("\n\nRESULTADO: ");
        int caminho = t;

        System.out.println(" "+ t +" - "+ cidades.get(t)+" <- \t\t");
        
        while (caminho != s){
            System.out.println(path[caminho] +" - "+ cidades.get(path[caminho])+" \t");
            caminho = path[caminho];

            if (caminho != s){
                System.out.println(" \t <- \t");
            }
        }
        System.out.println("\n\ncusto: "+ dist[k] +"\n\n");
        /****************************************/ 
        
    }
    
    public void carregarCombo(JComboBox combo, List lista) throws Exception  {
        
        combo.setModel( new DefaultComboBoxModel( lista.toArray() ) );
    }
}
