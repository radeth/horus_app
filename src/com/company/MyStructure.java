package com.company;

import java.util.List;

public class MyStructure implements IMyStructure {
    private List<INode> nodes;


    @Override
    public INode findByCode(String code) {
        for(INode node: nodes){
            if(node.getCode().equals(code)){
                return node;
            }
        }
        return null;
    }

    @Override
    public INode findByRenderer(String renderer) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}

interface INode {
    String getCode();
    String getRenderer();
}


interface ICompositeNode extends INode {
    List<INode> getNodes();
}


interface IMyStructure {
    // zwraca węzeł o podanym kodzie lub null
    INode findByCode(String code);
    // zwraca węzeł o podanym rendererze lub null
    INode findByRenderer(String renderer);
    //zwraca liczbę węzłów
    int count();
}