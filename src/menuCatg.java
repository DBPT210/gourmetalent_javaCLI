public class menuCatg {
    public menuCatg(int catg) {
        switch (catg){
            case 1:
                catgSopa();
                break;
            case 2:
                catgCarne();
                break;
            case 3:
                catgPeixe();
                break;
            case 4:
                catgSobrem();
                break;
        }
    }
    public void catgSopa(){
        System.out.println("Categoria: Sopas e Cozidos;");
    }
    public void catgCarne(){
        System.out.println("Categoria: Carne;");
    }
    public void catgPeixe(){
        System.out.println("Categoria: Peixe;");
    }
    public void catgSobrem(){
        System.out.println("Categoria: Sobremesas;");
    }
}
