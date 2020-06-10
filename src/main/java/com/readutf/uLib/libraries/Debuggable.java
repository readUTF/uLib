package com.readutf.uLib.libraries;

import com.readutf.uLib.uLib;

public abstract class Debuggable {

    public void debug(String s) {

        uLib.verbose(getClass(), s);

    }

}
