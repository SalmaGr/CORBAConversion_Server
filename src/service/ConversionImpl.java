package service;
public class ConversionImpl extends corbaConversion.IConversionRemotePOA {

    @Override
    public double conversionMontant(double mt) {
        return mt*3.3;
        //Conversion Euro -> dt
    }
}