<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" EnableCrashHandler="0" Version="1.0.8.20121008" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="0" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="5">
        <Driver Type="vrDriverDirectInput" />
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="1" VSync="1" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HandNode" Tag="Hand" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HeadNode" Tag="Head" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,1.700000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <CameraStereo Name="CameraStereo_Front" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="1000" Screen="ScreenFront" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.25" InterEyeDistance="0.065" />
        <CameraStereo Name="CameraStereo_Floor" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="1000" Screen="ScreenFloor" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.25" InterEyeDistance="0.065" />
        <Node3D Name="Screens" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Screen Name="ScreenFront" Parent="Screens" Tracker="0" PositionLocal="0.000000,1.000000,1.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" Width="2.5" Height="2" />
        <Screen Name="ScreenFloor" Parent="Screens" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.707107,0.000000,0.000000,0.707107" Width="2.5" Height="2" />
        <Viewport Name="Viewport_Front" Left="0" Top="0" Width="1280" Height="1024" Camera="CameraStereo_Front" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" />
        <Viewport Name="Viewport_Floor" Left="0" Top="0" Width="1280" Height="1024" Camera="CameraStereo_Floor" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" />
    </DisplayManager>
    <ClusterManager NVidiaSwapLock="0" DisableVSyncOnServer="0" ForceOpenGLConversion="0" BigBarrier="0" SimulateClusterLag="0">
        <ClusterServer Address="localhost" Viewport="Viewport_Front" CPUAffinity="-1" GPUAffinity="-1" />
        <ClusterClient Address="localhost" ClusterID="Client0" Viewport="Viewport_Floor" CPUAffinity="-1" GPUAffinity="-1" />
    </ClusterManager>
</MiddleVR>
