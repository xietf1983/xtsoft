// **********************************************************************
//
// Copyright (c) 2003-2008 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.0

package com.xtsoft.GeMss;

final class _AMD_MssWeb_QueryAlarmStorageV3 extends IceInternal.IncomingAsync implements AMD_MssWeb_QueryAlarmStorageV3
{
    public
    _AMD_MssWeb_QueryAlarmStorageV3(IceInternal.Incoming in)
    {
        super(in);
    }

    public void
    ice_response(int __ret, TUrlAddress[] seqUrlAddress)
    {
        if(__validateResponse(true))
        {
            try
            {
                IceInternal.BasicStream __os = this.__os();
                TUrlAddressSeqHelper.write(__os, seqUrlAddress);
                __os.writeInt(__ret);
            }
            catch(Ice.LocalException __ex)
            {
                ice_exception(__ex);
            }
            __response(true);
        }
    }

    public void
    ice_exception(java.lang.Exception ex)
    {
        if(__validateException(ex))
        {
            __exception(ex);
        }
    }
}
