import argparse
import os
import shutil


class Deploy:
    def __init__(self):
        self.deploy_dir_base = "target"
        self.deploy_dir_factory_container = self.deploy_dir_base + "\\factory-container"
        self.deploy_dir_lego_container_1 = self.deploy_dir_base + "\\lego-container-1"
        self.deploy_dir_lego_container_2 = self.deploy_dir_base + "\\lego-container-2"
        self.deploy_dir_remote_container = self.deploy_dir_base + "\\remote-container"

        self.jar_dir_factory_container = "factory-container\\target"
        self.jar_dir_lego_container_1 = "lego-container\\target"
        self.jar_dir_lego_container_2 = "lego-container\\target"
        self.jar_dir_remote_container = "remote-container\\target"

        self.resources_dir = "resources"
        self.resources_dir_factory_container = self.resources_dir + "\\factory-container"
        self.resources_dir_lego_container_1 = self.resources_dir + "\\lego-container-1"
        self.resources_dir_lego_container_2 = self.resources_dir + "\\lego-container-2"
        self.resources_dir_remote_container = self.resources_dir + "\\remote-container"

    def clean(self):
        print("deleting all artifacts from previous deployment ...")
        if os.path.exists(self.deploy_dir_base):
            shutil.rmtree(self.deploy_dir_base)

    def create_folders(self):
        print("creating deployment structure ...")
        os.mkdir(self.deploy_dir_base)
        os.mkdir(self.deploy_dir_factory_container)
        os.mkdir(self.deploy_dir_lego_container_1)
        os.mkdir(self.deploy_dir_lego_container_2)
        os.mkdir(self.deploy_dir_remote_container)

    def copy_jar_files(self):
        print("deploying jar files ...")
        shutil.copy2(self.jar_dir_factory_container + "\\factory-container-0.0.1-SNAPSHOT.jar",
                     self.deploy_dir_factory_container)
        shutil.copy2(self.jar_dir_lego_container_1 + "\\lego-container-0.0.1-SNAPSHOT.jar",
                     self.deploy_dir_lego_container_1)
        shutil.copy2(self.jar_dir_lego_container_2 + "\\lego-container-0.0.1-SNAPSHOT.jar",
                     self.deploy_dir_lego_container_2)
        shutil.copy2(self.jar_dir_remote_container + "\\remote-container-0.0.1-SNAPSHOT.jar",
                     self.deploy_dir_remote_container)

    def copy_configuration_files(self):
        print("deploying configuration files ...")
        shutil.copy2(self.resources_dir_factory_container + "\\configuration.xml", self.deploy_dir_factory_container)
        shutil.copy2(self.resources_dir_lego_container_1 + "\\configuration.xml", self.deploy_dir_lego_container_1)
        shutil.copy2(self.resources_dir_lego_container_2 + "\\configuration.xml", self.deploy_dir_lego_container_2)
        shutil.copy2(self.resources_dir_remote_container + "\\configuration.xml", self.deploy_dir_remote_container)


class Application:
    def __init__(self):
        self.deploy = Deploy()

    def run(self):
        parser = argparse.ArgumentParser()
        parser.add_argument("-a", "--artifact",
                            help="specify artifacts to deploy",
                            choices=["all", "clean", "configuration", "jar"])
        args = parser.parse_args()
        if args.artifact == "all":
            self.action_all()
        elif args.artifact == "clean":
            self.action_clean()
        elif args.artifact == "configuration":
            self.action_configuration()
        elif args.artifact == "jar":
            self.action_jar()
        else:
            parser.print_usage()

    def action_all(self):
        self.deploy.clean()
        self.deploy.create_folders()
        self.deploy.copy_jar_files()
        self.deploy.copy_configuration_files()
        print("done")

    def action_clean(self):
        self.deploy.clean()
        print("done")

    def action_configuration(self):
        self.deploy.copy_configuration_files()
        print("done")

    def action_jar(self):
        self.deploy.copy_jar_files()
        print("done")


if __name__ == '__main__':
    Application().run()
