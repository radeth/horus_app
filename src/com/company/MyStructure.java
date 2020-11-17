package com.company;

import java.util.ArrayList;
import java.util.List;

public class MyStructure implements IMyStructure {
    private List<INode> nodes = new ArrayList<>();



    @Override
    public INode findByCode(String code) {
        for(INode node: nodes){
            if (node instanceof ICompositeNode) {
                List<INode> flatModelNodes = getFlatModel((((ICompositeNode) node).getNodes()));
                for (INode iNodeFromFlatModel : flatModelNodes) {
                    if (iNodeFromFlatModel.getCode().equals(code)) {
                        return iNodeFromFlatModel;
                    }
                }
            } else {
                if (node.getCode().equals(code)) {
                    return node;
                }
            }
        }
        return null;
    }


    @Override
    public INode findByRenderer(String renderer) {
        for (INode node : nodes) {
            if (node instanceof ICompositeNode) {
                List<INode> flatModelNodes = getFlatModel((((ICompositeNode) node).getNodes()));
                for (INode iNodeFromFlatModel : flatModelNodes) {
                    if (iNodeFromFlatModel.getRenderer().equals(renderer)) {
                        return iNodeFromFlatModel;
                    }
                }
            } else {
                if (node.getRenderer().equals(renderer)) {
                    return node;
                }
            }
        }
        return null;
    }

    @Override
    public int count() {
        List<INode> allNodes = new ArrayList<>();
        for (INode node : nodes) {
            if (node instanceof ICompositeNode) {
                List<INode> flatModelNodes = getFlatModel((((ICompositeNode) node).getNodes()));
                allNodes.addAll(flatModelNodes);
            } else {
                allNodes.add(node);
            }
        }
        return allNodes.size();
    }

    private List<INode> getFlatModel(List<INode> iNodeList) {
        Boolean isCompositeNodePresent = false;
        for (INode iNode : iNodeList) {
            if (!(iNode instanceof ICompositeNode)) {
                iNodeList.add(iNode);
            } else {
                isCompositeNodePresent = true;
                iNodeList.addAll(((ICompositeNode) iNode).getNodes());
            }
        }
        if (isCompositeNodePresent) {
            return getFlatModel(iNodeList);
        } else {
            return iNodeList;

        }
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
