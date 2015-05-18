<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" EnableCrashHandler="0" Version="1.0.5.20120807" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="0" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="1684622684">
        <Driver Type="vrDriverDirectInput" />
        <Driver Type="vrDriverVRPN">
            <Tracker Address="Tracker0@localhost" ChannelIndex="0" ChannelsNb="1" Name="SpaceMouseTracker" Right="X" Front="Y" Up="Z" NeutralPosition="0.000000,0.000000,0.000000" WaitForData="0" />
            <Axis Address="device0@localhost" ChannelIndex="0" ChannelsNb="6" Name="SpaceMouseAxis" />
            <Buttons Address="device0@localhost" ChannelIndex="0" ChannelsNb="8" Name="SpaceMouseButtons" />
        </Driver>
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="1" VSync="0" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="SpaceMouseTracker.Tracker0" />
        <Node3D Name="HandNode" Tag="Hand" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HeadNode" Tag="Head" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Camera Name="Camera0" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" VerticalFOV="60" Near="0.1" Far="1000" Screen="0" ScreenDistance="1" UseViewportAspectRatio="1" AspectRatio="1.33333" />
        <Viewport Name="Viewport0" Left="0" Top="0" Width="1680" Height="1050" Camera="Camera0" Stereo="0" StereoMode="3" CompressSideBySide="0" StereoInvertEyes="0" />
    </DisplayManager>
    <ClusterManager SoftSwapLock="1" NVidiaSwapLock="0" DisableVSyncOnServer="1" ForceOpenGLConversion="0" AutomaticLicenseServerDiscovery="0" SimulateClusterLag="0" />
</MiddleVR>
