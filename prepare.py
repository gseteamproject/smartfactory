import os
import shutil


# variables

jar_dir_factory_container = "factory-container\\target"
jar_dir_lego_container_1 = "lego-container\\target"
jar_dir_lego_container_2 = "lego-container\\target"
jar_dir_remote_container = "remote-container\\target"

resources_dir = "resources"
resources_dir_factory_container = resources_dir + "\\factory-container"
resources_dir_lego_container_1 = resources_dir + "\\lego-container-1"
resources_dir_lego_container_2 = resources_dir + "\\lego-container-2"
resources_dir_remote_container = resources_dir + "\\remote-container"

deploy_dir_base = "target"
deploy_dir_factory_container = deploy_dir_base + "\\factory-container"
deploy_dir_lego_container_1 = deploy_dir_base + "\\lego-container-1"
deploy_dir_lego_container_2 = deploy_dir_base + "\\lego-container-2"
deploy_dir_remote_container = deploy_dir_base + "\\remote-container"


# main

if os.path.exists(deploy_dir_base):
    print("deleting old deploy directory...")
    shutil.rmtree(deploy_dir_base)


print("creating new deploy directory...")
os.mkdir(deploy_dir_base)

print("preparing factory-container deploy...")
os.mkdir(deploy_dir_factory_container)
shutil.copy2(jar_dir_factory_container + "\\factory-container-0.0.1-SNAPSHOT.jar", deploy_dir_factory_container)
shutil.copy2(resources_dir_factory_container + "\\configuration.xml", deploy_dir_factory_container)


print("preparing lego-container-1 deploy...")
os.mkdir(deploy_dir_lego_container_1)
shutil.copy2(jar_dir_lego_container_1 + "\\lego-container-0.0.1-SNAPSHOT.jar", deploy_dir_lego_container_1)
shutil.copy2(resources_dir_lego_container_1 + "\\configuration.xml", deploy_dir_lego_container_1)


print("preparing lego-container-2 deploy...")
os.mkdir(deploy_dir_lego_container_2)
shutil.copy2(jar_dir_lego_container_2 + "\\lego-container-0.0.1-SNAPSHOT.jar", deploy_dir_lego_container_2)
shutil.copy2(resources_dir_lego_container_2 + "\\configuration.xml", deploy_dir_lego_container_2)


print("preparing remote-container deploy...")
os.mkdir(deploy_dir_remote_container)
shutil.copy2(jar_dir_remote_container + "\\remote-container-0.0.1-SNAPSHOT.jar", deploy_dir_remote_container)
shutil.copy2(resources_dir_remote_container + "\\configuration.xml", deploy_dir_remote_container)


print("done")
