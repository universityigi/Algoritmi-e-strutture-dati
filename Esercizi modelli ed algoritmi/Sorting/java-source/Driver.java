public class Driver {
    public static void main(String[] argv) {
        Utils utils = new Utils();
        if((argv.length < 3) || (argv.length > 5)){
            utils.printHelp();
            return;
        }
        // TEST
        if (argv[0].equals("test")) {
            if(argv.length != 4){
                utils.printHelp();
                return;
            }
            if(utils.checkSortAlg(argv[1])){
                int size = utils.checkSize(Integer.parseInt(argv[3]));
                int[] arr = utils.generator(size, argv[2]);

                utils.test(argv[1], arr);
            }
            else{
                utils.printHelpSort();
                return;
            }
        }
        // RUN
        else if (argv[0].equals("run")) {
            if(argv.length != 4){
                utils.printHelp();
                return;
            }
            if(utils.checkSortAlg(argv[1])){
                int size = utils.checkSize(Integer.parseInt(argv[3]));
                int[] arr = utils.generator(size, argv[2]);

                utils.run(argv[1], arr);
            }
            else{
                utils.printHelpSort();
                return;
            }
        }
        // CMP
        else if (argv[0].equals("cmp")) {
            if(argv.length != 5){
                utils.printHelp();
                return;
            }
            if(utils.checkSortAlg(argv[1]) && utils.checkSortAlg(argv[2])){
                int size = utils.checkSize(Integer.parseInt(argv[4]));
                int[] arr = utils.generator(size, argv[3]);

                utils.run(argv[1], arr);
                utils.run(argv[2], arr);
            }
            else{
                utils.printHelpSort();
                return;
            }
        }
        // FILE
        else if (argv[0].equals("file")) {
            if(argv.length != 3){
                utils.printHelp();
                return;
            }
            if(utils.checkSortAlg(argv[1]))
                utils.file(argv[1], argv[2]);
            else{
                utils.printHelpSort();
                return;
            }
        }
        // GEN
        else if (argv[0].equals("gen")) {
            if(argv.length != 4){
                utils.printHelp();
                return;
            }
            int size = utils.checkSize(Integer.parseInt(argv[2]));
            utils.arrayToFile(argv[1], size, argv[3]);
            return;
        }
        else utils.printHelp();
    }
}