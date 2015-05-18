<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" EnableCrashHandler="0" Version="1.0.8.20121008" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="0" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="5">
        <Driver Type="vrDriverDirectInput" />
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="1" VSync="1" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="TrackerSimulatorMouse0.Tracker" />
        <Node3D Name="HandNode" Tag="Hand" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HeadNode" Tag="Head" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Camera Name="Camera0" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,-0.000001,0.000000" VerticalFOV="60" Near="0.1" Far="1000" Screen="Screen0" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1" />
        <Camera Name="Camera1" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,-0.000001,0.000000" VerticalFOV="60" Near="0.1" Far="1000" Screen="Screen1" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1" />
        <Screen Name="Screen0" Parent="HeadNode" Tracker="0" PositionLocal="-0.500000,2.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" Width="1" Height="1" />
        <Screen Name="Screen1" Parent="HeadNode" Tracker="0" PositionLocal="0.500000,2.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" Width="1" Height="1" />
        <Viewport Name="Viewport0" Left="0" Top="0" Width="840" Height="1050" Camera="Camera0" Stereo="0" StereoMode="3" CompressSideBySide="0" StereoInvertEyes="0" />
        <Viewport Name="Viewport1" Left="840" Top="0" Width="840" Height="1050" Camera="Camera1" Stereo="0" StereoMode="3" CompressSideBySide="0" StereoInvertEyes="0" />
    </DisplayManager>
    <Scripts>
        <Script Type="TrackerSimulatorMouse" Name="TrackerSimulatorMouse0" />
    </Scripts>
    <ClusterManager NVidiaSwapLock="0" DisableVSyncOnServer="0" ForceOpenGLConversion="1" BigBarrier="0" SimulateClusterLag="0">
        <ClusterServer Address="localhost" Viewport="Viewport0" CPUAffinity="-1" GPUAffinity="-1" />
        <ClusterClient Address="localhost" ClusterID="Client0" Viewport="Viewport1" CPUAffinity="-1" GPUAffinity="-1" />
    </ClusterManager>
</MiddleVR>
