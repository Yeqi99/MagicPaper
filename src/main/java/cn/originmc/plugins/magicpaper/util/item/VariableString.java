package cn.originmc.plugins.magicpaper.util.item;


import java.util.ArrayList;
import java.util.List;

public class VariableString {
    private String originString;
    private String resultString;
    private char sign;
    public VariableString(String originString){
        setOriginString(originString);
        setSign('*');
        setResultString(getOriginString());
    }
    public VariableString(String originString, char sign){
        setOriginString(originString);
        setSign(sign);
        setResultString(getOriginString());
    }
    public List<String> getAllVariable(){
        List<String> variables=new ArrayList<>();
        for (int i=0;i<getOriginString().length();i++){
            char nowChar = getOriginString().charAt(i);
            if (nowChar!= getSign()){
                continue;
            }
            if (getOriginString().length() <= i + 1) {
                continue;
            }
            if (getOriginString().charAt(i + 1) == getSign()) {
                i++;
            } else {
                StringBuilder funName = new StringBuilder();
                int nextSignIndex = i + 1;
                char funNameChar = getOriginString().charAt(nextSignIndex);
                while (funNameChar != getSign()) {
                    funName.append(funNameChar);
                    nextSignIndex++;
                    funNameChar = getOriginString().charAt(nextSignIndex);
                }
                variables.add(funName.toString());
                i = nextSignIndex;
            }
        }
        return variables;
    }
    public String getVariable(int index){
        List<String> variableList=getAllVariable();
        if (variableList.size()>index){
            return variableList.get(index);
        }else {
            return "";
        }
    }

    /**
     * 将指定变量替换为对应值
     * @param var 变量名
     * @param value 对应值
     */
    public void setVariable(String var,String value){
        StringBuilder clone=new StringBuilder();
        for (int i=0;i<getResultString().length();i++){
            char nowChar = getResultString().charAt(i);
            if (nowChar!= getSign()){
                clone.append(nowChar);
                continue;
            }
            if (getResultString().length() <= i + 1) {
                continue;
            }
            if (getResultString().charAt(i + 1) == getSign()) {
                i++;
            } else {
                StringBuilder funName = new StringBuilder();
                int nextSignIndex = i + 1;
                char funNameChar = getResultString().charAt(nextSignIndex);
                while (funNameChar != getSign()) {
                    funName.append(funNameChar);
                    nextSignIndex++;
                    funNameChar = getResultString().charAt(nextSignIndex);
                }
                if (funName.toString().equalsIgnoreCase(var)){
                    clone.append(value);
                }else {
                    clone.append(getSign()).append(funName).append(getSign());
                }
                i = nextSignIndex;
            }
        }
        setResultString(clone.toString());
    }
    public boolean setVariable(int index,String value){
        if (index<0){
            return false;
        }
        if (getVariableAmount()<=index){
            return false;
        }
        int nowIndex=0;
        StringBuilder clone=new StringBuilder();
        for (int i=0;i<getResultString().length();i++){
            char nowChar = getResultString().charAt(i);
            if (nowChar!= getSign()){
                clone.append(nowChar);
                continue;
            }
            if (getResultString().length() <= i + 1) {
                continue;
            }
            if (getResultString().charAt(i + 1) == getSign()) {
                i++;
            } else {
                StringBuilder funName = new StringBuilder();
                int nextSignIndex = i + 1;
                char funNameChar = getResultString().charAt(nextSignIndex);
                while (funNameChar != getSign()) {
                    funName.append(funNameChar);
                    nextSignIndex++;
                    funNameChar = getResultString().charAt(nextSignIndex);
                }
                if (nowIndex==index){
                    clone.append(value);
                }else {
                    clone.append(getSign()).append(funName).append(getSign());
                    nowIndex++;
                }
                i = nextSignIndex;
            }
        }
        setResultString(clone.toString());
        return true;
    }
    public int getVariableAmount(){
        int amount=0;
        for (int i=0;i<getResultString().length();i++){
            char nowChar = getResultString().charAt(i);
            if (nowChar!= getSign()){
                continue;
            }
            if (getResultString().length() <= i + 1) {
                continue;
            }
            if (getResultString().charAt(i + 1) == getSign()) {
                i++;
            } else {
                int nextSignIndex = i + 1;
                char funNameChar = getResultString().charAt(nextSignIndex);
                while (funNameChar != getSign()) {
                    nextSignIndex++;
                    funNameChar = getResultString().charAt(nextSignIndex);
                }
                amount++;
                i = nextSignIndex;
            }
        }
        return amount;
    }
    public String getOriginString() {
        return originString;
    }

    public void setOriginString(String originString) {
        this.originString = originString;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
}

