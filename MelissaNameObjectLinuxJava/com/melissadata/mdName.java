package com.melissadata;

public class mdName {
	private long I;
	protected boolean ownMemory;

	protected static long getI(mdName obj) {
		return (obj==null ? 0 : obj.I);
	}

	protected void finalize() {
		delete();
	}

public final static class ProgramStatus {
	public final static mdName.ProgramStatus NoError=new mdName.ProgramStatus("NoError",0);
	public final static mdName.ProgramStatus ConfigFile=new mdName.ProgramStatus("ConfigFile",1);
	public final static mdName.ProgramStatus LicenseExpired=new mdName.ProgramStatus("LicenseExpired",2);
	public final static mdName.ProgramStatus DatabaseExpired=new mdName.ProgramStatus("DatabaseExpired",3);
	public final static mdName.ProgramStatus Unknown=new mdName.ProgramStatus("Unknown",4);

	private final String enumName;
	private final int enumValue;
	private static ProgramStatus[] enumValues={NoError,ConfigFile,LicenseExpired,DatabaseExpired,Unknown};

	private ProgramStatus(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static ProgramStatus toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+ProgramStatus.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class NameHints {
	public final static mdName.NameHints DefinitelyFull=new mdName.NameHints("DefinitelyFull",1);
	public final static mdName.NameHints VeryLikelyFull=new mdName.NameHints("VeryLikelyFull",2);
	public final static mdName.NameHints ProbablyFull=new mdName.NameHints("ProbablyFull",3);
	public final static mdName.NameHints Varying=new mdName.NameHints("Varying",4);
	public final static mdName.NameHints ProbablyInverse=new mdName.NameHints("ProbablyInverse",5);
	public final static mdName.NameHints VeryLikelyInverse=new mdName.NameHints("VeryLikelyInverse",6);
	public final static mdName.NameHints DefinitelyInverse=new mdName.NameHints("DefinitelyInverse",7);
	public final static mdName.NameHints MixedFirstName=new mdName.NameHints("MixedFirstName",8);
	public final static mdName.NameHints MixedLastName=new mdName.NameHints("MixedLastName",9);

	private final String enumName;
	private final int enumValue;
	private static NameHints[] enumValues={DefinitelyFull,VeryLikelyFull,ProbablyFull,Varying,ProbablyInverse,VeryLikelyInverse,DefinitelyInverse,MixedFirstName,MixedLastName};

	private NameHints(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static NameHints toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+NameHints.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class Population {
	public final static mdName.Population Male=new mdName.Population("Male",1);
	public final static mdName.Population Mixed=new mdName.Population("Mixed",2);
	public final static mdName.Population Female=new mdName.Population("Female",3);

	private final String enumName;
	private final int enumValue;
	private static Population[] enumValues={Male,Mixed,Female};

	private Population(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static Population toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+Population.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class Aggression {
	public final static mdName.Aggression Aggressive=new mdName.Aggression("Aggressive",1);
	public final static mdName.Aggression Neutral=new mdName.Aggression("Neutral",2);
	public final static mdName.Aggression Conservative=new mdName.Aggression("Conservative",3);

	private final String enumName;
	private final int enumValue;
	private static Aggression[] enumValues={Aggressive,Neutral,Conservative};

	private Aggression(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static Aggression toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+Aggression.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class Salutations {
	public final static mdName.Salutations Formal=new mdName.Salutations("Formal",0);
	public final static mdName.Salutations Informal=new mdName.Salutations("Informal",1);
	public final static mdName.Salutations FirstLast=new mdName.Salutations("FirstLast",2);
	public final static mdName.Salutations Slug=new mdName.Salutations("Slug",3);
	public final static mdName.Salutations Blank=new mdName.Salutations("Blank",4);

	private final String enumName;
	private final int enumValue;
	private static Salutations[] enumValues={Formal,Informal,FirstLast,Slug,Blank};

	private Salutations(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static Salutations toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+Salutations.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class MiddleNameLogic {
	public final static mdName.MiddleNameLogic ParseLogic=new mdName.MiddleNameLogic("ParseLogic",0);
	public final static mdName.MiddleNameLogic HyphenatedLast=new mdName.MiddleNameLogic("HyphenatedLast",1);
	public final static mdName.MiddleNameLogic MiddleName=new mdName.MiddleNameLogic("MiddleName",2);

	private final String enumName;
	private final int enumValue;
	private static MiddleNameLogic[] enumValues={ParseLogic,HyphenatedLast,MiddleName};

	private MiddleNameLogic(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static MiddleNameLogic toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+MiddleNameLogic.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class ResultCdDescOpt {
	public final static mdName.ResultCdDescOpt ResultCodeDescriptionLong=new mdName.ResultCdDescOpt("ResultCodeDescriptionLong",0);
	public final static mdName.ResultCdDescOpt ResultCodeDescriptionShort=new mdName.ResultCdDescOpt("ResultCodeDescriptionShort",1);

	private final String enumName;
	private final int enumValue;
	private static ResultCdDescOpt[] enumValues={ResultCodeDescriptionLong,ResultCodeDescriptionShort};

	private ResultCdDescOpt(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static ResultCdDescOpt toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+ResultCdDescOpt.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

	protected mdName(long i,boolean own) {
		ownMemory=own;
		I=i;
	}

	public mdName() {
		this(mdNameJNI.mdNameCreate(),true);
	}

	public synchronized void delete() {
		if (I!=0) {
			if (ownMemory) {
				ownMemory=false;
				mdNameJNI.mdNameDestroy(I);
			}
			I=0;
		}
	}

	public void SetPathToNameFiles(String p1) {
		mdNameJNI.SetPathToNameFiles(I,p1);
	}

	public ProgramStatus InitializeDataFiles() {
		return ProgramStatus.toEnum(mdNameJNI.InitializeDataFiles(I));
	}

	public String GetInitializeErrorString() {
		return mdNameJNI.GetInitializeErrorString(I);
	}

	public int SetLicenseString(String p1) {
		return mdNameJNI.SetLicenseString(I,p1);
	}

	public String GetBuildNumber() {
		return mdNameJNI.GetBuildNumber(I);
	}

	public String GetDatabaseDate() {
		return mdNameJNI.GetDatabaseDate(I);
	}

	public String GetDatabaseExpirationDate() {
		return mdNameJNI.GetDatabaseExpirationDate(I);
	}

	public String GetLicenseExpirationDate() {
		return mdNameJNI.GetLicenseExpirationDate(I);
	}

	public int SetPrimaryNameHint(mdName.NameHints p1) {
		return mdNameJNI.SetPrimaryNameHint(I,p1.toValue());
	}

	public int SetSecondaryNameHint(mdName.NameHints p1) {
		return mdNameJNI.SetSecondaryNameHint(I,p1.toValue());
	}

	public int SetFirstNameSpellingCorrection(int p1) {
		return mdNameJNI.SetFirstNameSpellingCorrection(I,p1);
	}

	public int SetMiddleNameLogic(mdName.MiddleNameLogic p1) {
		return mdNameJNI.SetMiddleNameLogic(I,p1.toValue());
	}

	public int SetGenderPopulation(mdName.Population p1) {
		return mdNameJNI.SetGenderPopulation(I,p1.toValue());
	}

	public int SetGenderAggression(mdName.Aggression p1) {
		return mdNameJNI.SetGenderAggression(I,p1.toValue());
	}

	public int AddSalutation(mdName.Salutations p1) {
		return mdNameJNI.AddSalutation(I,p1.toValue());
	}

	public void SetSalutationPrefix(String p1) {
		mdNameJNI.SetSalutationPrefix(I,p1);
	}

	public void SetSalutationSuffix(String p1) {
		mdNameJNI.SetSalutationSuffix(I,p1);
	}

	public void SetSalutationSlug(String p1) {
		mdNameJNI.SetSalutationSlug(I,p1);
	}

	public void ClearProperties() {
		mdNameJNI.ClearProperties(I);
	}

	public void SetFullName(String p1) {
		mdNameJNI.SetFullName(I,p1);
	}

	public void SetPrefix(String p1) {
		mdNameJNI.SetPrefix(I,p1);
	}

	public void SetPrefix2(String p1) {
		mdNameJNI.SetPrefix2(I,p1);
	}

	public void SetFirstName(String p1) {
		mdNameJNI.SetFirstName(I,p1);
	}

	public void SetFirstName2(String p1) {
		mdNameJNI.SetFirstName2(I,p1);
	}

	public void SetMiddleName(String p1) {
		mdNameJNI.SetMiddleName(I,p1);
	}

	public void SetMiddleName2(String p1) {
		mdNameJNI.SetMiddleName2(I,p1);
	}

	public void SetSuffix(String p1) {
		mdNameJNI.SetSuffix(I,p1);
	}

	public void SetSuffix2(String p1) {
		mdNameJNI.SetSuffix2(I,p1);
	}

	public void SetLastName(String p1) {
		mdNameJNI.SetLastName(I,p1);
	}

	public void SetLastName2(String p1) {
		mdNameJNI.SetLastName2(I,p1);
	}

	public int Parse() {
		return mdNameJNI.Parse(I);
	}

	public int Genderize() {
		return mdNameJNI.Genderize(I);
	}

	public int Salutate() {
		return mdNameJNI.Salutate(I);
	}

	public String GetStatusCode() {
		return mdNameJNI.GetStatusCode(I);
	}

	public String GetErrorCode() {
		return mdNameJNI.GetErrorCode(I);
	}

	public String GetChangeCode() {
		return mdNameJNI.GetChangeCode(I);
	}

	public String GetDebugInfo(String p1) {
		return mdNameJNI.GetDebugInfo(I,p1);
	}

	public String GetResults() {
		return mdNameJNI.GetResults(I);
	}

	public String GetResultCodeDescription(String p1, mdName.ResultCdDescOpt opt) {
		return mdNameJNI.GetResultCodeDescription(I,p1,opt.toValue());
	}

	public String GetPrefix() {
		return mdNameJNI.GetPrefix(I);
	}

	public String GetPrefix2() {
		return mdNameJNI.GetPrefix2(I);
	}

	public String GetFirstName() {
		return mdNameJNI.GetFirstName(I);
	}

	public String GetFirstName2() {
		return mdNameJNI.GetFirstName2(I);
	}

	public String GetMiddleName() {
		return mdNameJNI.GetMiddleName(I);
	}

	public String GetMiddleName2() {
		return mdNameJNI.GetMiddleName2(I);
	}

	public String GetLastName() {
		return mdNameJNI.GetLastName(I);
	}

	public String GetLastName2() {
		return mdNameJNI.GetLastName2(I);
	}

	public String GetSuffix() {
		return mdNameJNI.GetSuffix(I);
	}

	public String GetSuffix2() {
		return mdNameJNI.GetSuffix2(I);
	}

	public String GetGender() {
		return mdNameJNI.GetGender(I);
	}

	public String GetGender2() {
		return mdNameJNI.GetGender2(I);
	}

	public String GetSalutation() {
		return mdNameJNI.GetSalutation(I);
	}

	public String StandardizeCompany(String p1) {
		return mdNameJNI.StandardizeCompany(I,p1);
	}

	public void SetReserved(String p1, String p2) {
		mdNameJNI.SetReserved(I,p1,p2);
	}

	public String GetReserved(String p1) {
		return mdNameJNI.GetReserved(I,p1);
	}

}
