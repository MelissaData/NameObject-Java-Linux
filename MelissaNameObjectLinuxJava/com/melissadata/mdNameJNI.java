package com.melissadata;

public class mdNameJNI {
	static {
		try {
			System.loadLibrary("mdNameJavaWrapper");
		} catch (UnsatisfiedLinkError ule) {
			System.out.println(ule);
			System.out.println("java.library.path="+System.getProperty("java.library.path"));
		}
	}

	public final static native long mdNameCreate();
	public final static native void mdNameDestroy(long I);
	public final static native void SetPathToNameFiles(long I,String p1);
	public final static native int InitializeDataFiles(long I);
	public final static native String GetInitializeErrorString(long I);
	public final static native int SetLicenseString(long I,String p1);
	public final static native String GetBuildNumber(long I);
	public final static native String GetDatabaseDate(long I);
	public final static native String GetDatabaseExpirationDate(long I);
	public final static native String GetLicenseExpirationDate(long I);
	public final static native int SetPrimaryNameHint(long I,int p1);
	public final static native int SetSecondaryNameHint(long I,int p1);
	public final static native int SetFirstNameSpellingCorrection(long I,int p1);
	public final static native int SetMiddleNameLogic(long I,int p1);
	public final static native int SetGenderPopulation(long I,int p1);
	public final static native int SetGenderAggression(long I,int p1);
	public final static native int AddSalutation(long I,int p1);
	public final static native void SetSalutationPrefix(long I,String p1);
	public final static native void SetSalutationSuffix(long I,String p1);
	public final static native void SetSalutationSlug(long I,String p1);
	public final static native void ClearProperties(long I);
	public final static native void SetFullName(long I,String p1);
	public final static native void SetPrefix(long I,String p1);
	public final static native void SetPrefix2(long I,String p1);
	public final static native void SetFirstName(long I,String p1);
	public final static native void SetFirstName2(long I,String p1);
	public final static native void SetMiddleName(long I,String p1);
	public final static native void SetMiddleName2(long I,String p1);
	public final static native void SetSuffix(long I,String p1);
	public final static native void SetSuffix2(long I,String p1);
	public final static native void SetLastName(long I,String p1);
	public final static native void SetLastName2(long I,String p1);
	public final static native int Parse(long I);
	public final static native int Genderize(long I);
	public final static native int Salutate(long I);
	public final static native String GetStatusCode(long I);
	public final static native String GetErrorCode(long I);
	public final static native String GetChangeCode(long I);
	public final static native String GetResults(long I);
	public final static native String GetResultCodeDescription(long I,String p1,int opt);
	public final static native String GetPrefix(long I);
	public final static native String GetPrefix2(long I);
	public final static native String GetFirstName(long I);
	public final static native String GetFirstName2(long I);
	public final static native String GetMiddleName(long I);
	public final static native String GetMiddleName2(long I);
	public final static native String GetLastName(long I);
	public final static native String GetLastName2(long I);
	public final static native String GetSuffix(long I);
	public final static native String GetSuffix2(long I);
	public final static native String GetGender(long I);
	public final static native String GetGender2(long I);
	public final static native String GetSalutation(long I);
	public final static native String StandardizeCompany(long I,String p1);
	public final static native void SetReserved(long I,String p1,String p2);
	public final static native String GetReserved(long I,String p1);
}
